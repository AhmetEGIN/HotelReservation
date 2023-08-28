package com.egin.hotelreservation.user.repository;

import com.egin.hotelreservation.user.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Admin Entity nesnesinin veri tabanı işlemlerini yönetmek için kullanılacak
 * olan JPA interface'idir.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByEmail(String email);
    boolean existsAdminByEmailEquals(String email);

}
