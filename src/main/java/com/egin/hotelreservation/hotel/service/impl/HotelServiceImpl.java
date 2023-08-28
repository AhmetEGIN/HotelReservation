package com.egin.hotelreservation.hotel.service.impl;

import com.egin.hotelreservation.auth.dto.requests.LoginRequest;
import com.egin.hotelreservation.auth.dto.responses.TokenResponse;
import com.egin.hotelreservation.auth.service.AuthService;
import com.egin.hotelreservation.common.exception.AppException;
import com.egin.hotelreservation.common.helper.identity.UserInContext;
import com.egin.hotelreservation.hotel.dto.requests.hotelRequests.UpdateHotelRequest;
import com.egin.hotelreservation.hotel.exception.HotelNotFoundException;
import com.egin.hotelreservation.hotel.model.Hotel;
import com.egin.hotelreservation.hotel.model.enums.HotelStatus;
import com.egin.hotelreservation.hotel.repository.HotelRepository;
import com.egin.hotelreservation.hotel.service.HotelService;
import com.egin.hotelreservation.hotel.service.rules.HotelBusinessRules;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * {@link Hotel} entity nesneleri üzerinde işlemler yapmayı sağlayan Service katmanı class'ıdır
 */
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelBusinessRules hotelBusinessRules;
    private final AuthService authService;
    private final UserInContext userInContext;

    public HotelServiceImpl(
            HotelRepository hotelRepository,
            HotelBusinessRules hotelBusinessRules,
            AuthService authService,
            UserInContext userInContext) {
        this.hotelRepository = hotelRepository;
        this.hotelBusinessRules = hotelBusinessRules;
        this.authService = authService;
        this.userInContext = userInContext;
    }


    /**
     * Authenticate olmuş olan Hotel nesnesini Context' ten getirmeye yarar.
     * @return
     */
    private Hotel getUser() {
        Hotel hotel = this.hotelRepository
                .findByEmail(this.userInContext.getUsername())
                .orElseThrow(HotelNotFoundException::new);
//        System.out.println("-----------");
//        System.out.println(this.userInContext.getUsername());
//        Hotel hotel = this.hotelRepository.findHotelByEmail(this.userInContext.getUsername());
//        System.out.println("----------------------------");
//        System.out.println(hotel.getName());
        return hotel;
    }

    /**
     * Hotel entity nesnesin sistemde Login işlemlerini yapmasını sağlayan metottur.
     * @param loginRequest
     * @return
     */
    @Override
    public TokenResponse loginHotel(final LoginRequest loginRequest) {

        return this.authService.authenticate(loginRequest);
    }

    /**
     * Id değeri verilen {@link Hotel} nesnesini getirmeye yarayan Service katmanı metodudur.
     * @param id
     * @return
     */
    @Override
    public Hotel getHotelById(String id) {

        return this.hotelRepository.findById(id)
                .orElseThrow(HotelNotFoundException::new);
    }


    /**
     * {@link Hotel} entity nesnesi üzerinde Update işlemi için kullanılan
     * Service katmanı metodudur.
     * @param request
     * @return
     */
    @Override
    public Hotel update(UpdateHotelRequest request) {

        updateHotelInfo(getUser(), request);
        this.hotelRepository.save(getUser());

        return getUser();
    }


    /**
     * Kayıtlı olan tüm {@link Hotel} entity nesnelerini bir Liste halinde geri döndürecek
     * olan Service katmanı metodudur.
     * @return
     */
    @Override
    public List<Hotel> getAllHotels() {

        return this.hotelRepository.findAll();
    }

    /**
     * Email parametresinin değeri verilmiş bir {@link Hotel }nesnesini getirmek için
     * kullanılan Service katmanı metodudur.
     * @param email
     * @return
     */
    @Override
    public Hotel getHotelByEmail(String email) {
//        return this.hotelRepository.findHotelByEmail(email)
//                .orElseThrow(HotelNotFoundException::new);
        return null;
    }


    /**
     * Id değeri verilen bir {@link Hotel} entity nesnesini silme işleminde kullanılacak
     * olan Service katmanı metodudur.
     * @param id
     */
    @Override
    public void delete(String id) {

        Hotel hotel = getHotelById(id);
        this.hotelRepository.delete(hotel);
    }

    /**
     * Context' te Authenticate olmuş kullanıcının isDeleted parametresini güncellemek için
     * kullanılacak olan Service katmanı metodudur.
     * @param isDeleted
     */
    @Override
    public void softDelete(boolean isDeleted) {
        getUser().setDeleted(isDeleted);

    }

    /**
     * Id değeri verilmiş bir {@link Hotel} entity nesnesinin {@link HotelStatus} parametresini
     * güncellemek için kullanılacak olan Service katmanı metodudur.
     * @param id
     * @param hotelStatus
     * @return
     */
    @Override
    public Hotel changeHotelStatus(String id, HotelStatus hotelStatus) {

        Hotel hotel = getHotelById(id);
        hotel.setHotelStatus(hotelStatus);
        this.hotelRepository.save(hotel);

        return hotel;
    }


    /**
     * {@link Hotel} entity nesnelerini sayfalama, sıralama ve şehir bilgisine göre filtreleme işlemlerinde
     * kullanılacak olan Service katmanı metodudur.
     * @param pageNo
     * @param pageSize
     * @param sortKey
     * @param sortingDirection
     * @param cityId
     * @return
     */
    @Override
    public List<Hotel> getAllPageableAndSortedAndFilteredByCity(
            int pageNo,
            int pageSize,
            String sortKey,
            String sortingDirection,
            String cityId)
    {

        List<Hotel> hotels = getAllHotelsPageable(pageNo, pageSize, sortKey, sortingDirection);

        Predicate<Hotel> cityCondition = cityId == null
                ? (hotel -> true)
                : (hotel -> hotel.getCity().getId().equals(cityId)
        );

        hotels.stream()
                .filter(cityCondition)
                .collect(Collectors.toList());

        return hotels;
    }



    private List<Hotel> getAllHotelsPageable(int pageNo, int pageSize, String sortKey, String sortingDirection) {
        String sortKeyField;

        try {
            sortKeyField = Hotel.class.getDeclaredField(sortKey).getName();
        } catch (NoSuchFieldException e) {

            throw new AppException("Field Not Found");
        }
        Sort sort = Sort.by(Sort.Direction.valueOf(sortingDirection), sortKeyField);
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        List<Hotel> hotels = this.hotelRepository.findAll(pageable).stream().toList();
        return hotels;
    }


    private void updateHotelInfo(Hotel hotel, UpdateHotelRequest request) {
        hotel.setName(request.getName());
        hotel.setStar(request.getStar());
        hotel.setHotelType(request.getHotelType());
    }

}
