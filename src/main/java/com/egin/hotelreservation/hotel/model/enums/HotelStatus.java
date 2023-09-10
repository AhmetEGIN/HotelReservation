package com.egin.hotelreservation.hotel.model.enums;

import java.io.Serializable;

/**
 * Hotel entity nesnelerinin durumunu gösteren Enum'dır.
 */
public enum HotelStatus implements Serializable {

    APPROVALED,
    NOT_APPROVALED,
    WAIT_APPROVALED

}
