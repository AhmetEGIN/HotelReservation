package com.egin.hotelreservation.common.model;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "updated_user")
    private String updatedUser;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_deleted")
    private boolean isDeleted;



}
