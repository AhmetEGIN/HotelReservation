package com.egin.hotelreservation.security.config;

import com.egin.hotelreservation.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(POST, "/api/v1/*/login").permitAll()


                        .requestMatchers(POST, "/api/v1/admins").permitAll()


                        .requestMatchers(POST, "/api/v1/customers").permitAll()
                        .requestMatchers(GET, "/api/v1/customers/all").permitAll()
                        .requestMatchers(DELETE, "/api/v1/customers/soft").permitAll()
                        .requestMatchers(GET, "/api/v1/customers/{id}").permitAll()


                        .requestMatchers(POST, "/api/v1/hotels").permitAll()
//                        .requestMatchers(DELETE, "/api/v1/hotels").permitAll()
                        .requestMatchers(GET, "/api/v1/hotels").permitAll()
                        .requestMatchers(GET, "/api/v1/hotels/{id}").permitAll()
                        .requestMatchers(POST, "/api/v1/hotels/login").permitAll()


                        .requestMatchers(GET, "/api/v1/cities").permitAll()
                        .requestMatchers(GET, "/api/v1/cities/country-code").permitAll()
                        .requestMatchers(GET, "/api/v1/cities/{id}").permitAll()


                        .requestMatchers(GET, "/api/v1/rooms/all").permitAll()
                        .requestMatchers(GET, "/api/v1/rooms/{id}").permitAll()
                        .requestMatchers(GET, "/api/v1/rooms").permitAll()


                        .requestMatchers(GET, "/api/v1/room-bookings/price").permitAll()
                        .requestMatchers(POST, "/api/v1/room-bookings").permitAll()

                        .requestMatchers(
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()

                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handler -> {
                    handler.accessDeniedHandler(accessDeniedHandler);
                    handler.authenticationEntryPoint(authenticationEntryPoint);
                })
                .logout(
                        logout -> logout
                                .logoutUrl("/api/v1/auth/logout")
                                .logoutSuccessUrl("www.google.com")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler(
                                        (((request, response, authentication) ->
                                                SecurityContextHolder.clearContext()))
                                )
                );

        return httpSecurity.build();


    }


}
