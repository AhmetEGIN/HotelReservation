package com.egin.hotelreservation.auth.service.impl;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.auth.exceptions.PasswordNotValidException;
import com.egin.hotelreservation.auth.exceptions.UserNotFoundException;
import com.egin.hotelreservation.auth.service.AuthService;
import com.egin.hotelreservation.security.model.token.Token;
import com.egin.hotelreservation.security.model.token.TokenType;
import com.egin.hotelreservation.security.model.user.User;
import com.egin.hotelreservation.security.repository.TokenRepository;
import com.egin.hotelreservation.security.repository.UserRepository;
import com.egin.hotelreservation.security.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Register ve Login işlemlerinde Access token ve refresh token oluşturmayı sağlayan
 * servisin implementasyonudur.
 *
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
                TokenRepository tokenRepository,
                UserRepository userRepository,
                AuthenticationManager authenticationManager,
                JwtService jwtService,
                PasswordEncoder passwordEncoder) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Sisteme kayıt olan bir kullanıcı için access token ve refresh token oluşturma
     * işlemlerini gerçekleştiren metottur.
     * AccessToken ve RefreshToken bilgilerini geri döner.
     *
     * @param {{@link User}}
     * @return {{@link TokenResponse}}
     */
    @Override
    public TokenResponse generateToken(User user) {
        String jwtToken = this.jwtService.generateToken(user);
        String refreshToken = this.jwtService.generateRefreshToken(user);

        saveUserToken(user, jwtToken);

        return TokenResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Kullanıcıların sisteme login olma işlemlerini gerçekleştiren metottur.
     * AccessToken ve RefreshToken bilgilerini geri döner.
     *
     * @param {{@link LoginRequest}
     * @return {{@link TokenResponse}}
     */
    @Override
    public TokenResponse authenticate(LoginRequest loginRequest) {

        var auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = this.userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(UserNotFoundException::new);
        if (!this.passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new PasswordNotValidException();
        }
        var jwtToken = this.jwtService.generateToken(user);
        var refreshToken = this.jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return TokenResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    /**
     * Token yenilemek için kullanılır.
     * Refresh token bilgileri verilen kullanıcı için yeni bir access token oluşturmak için kullanılan metottur.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ;
        }

        refreshToken = authHeader.substring(7);
        userEmail = this.jwtService.extractUserEmail(refreshToken);

        if (userEmail != null) {
            var user = this.userRepository
                    .findByEmail(userEmail)
                    .orElseThrow(UserNotFoundException::new);

            if (this.jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = this.jwtService.generateToken(user);

                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);

                TokenResponse tokenResponse = TokenResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), tokenResponse);

            }
        }
    }


    /**
     * Kullanıcılar için oluşturulan access token bilgisini Token veri tabanına kaydetmek
     * için kullanılan metottur.
     *
     * @param user
     * @param jwtToken
     */
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        this.tokenRepository.save(token);

    }

    /**
     * Bir kullanıcı sisteme giriş yaptığında veya kullanıcının access token'ı yenilendiği
     * zaman kullanıcıya ait eski token'ların devre dışı kalmasını sağlayan metottur.
     *
     * @param {{@link User}
     *
     */
    private void revokeAllUserTokens(User user) {
        var validUserTokens = this.tokenRepository.findAllValidTokensByUser(user.getId());

        if (validUserTokens == null) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });

        this.tokenRepository.saveAll(validUserTokens);

    }



}
