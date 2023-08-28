package com.egin.hotelreservation.user.repository;

import com.egin.hotelreservation.user.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Customer Entity nesnesinin veri tabanı işlemlerini yönetmek için
 * kullanılacak olan JPA interface'idi.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsCustomerByEmailEquals(String email);
    Optional<Customer> findByEmail(String email);

}
