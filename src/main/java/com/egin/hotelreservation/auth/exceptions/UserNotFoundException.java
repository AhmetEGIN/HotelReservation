package com.egin.hotelreservation.auth.exceptions;

/**
 * Verilen bilgiler ile herhangi bir kullanıcı bulunamadığı zaman
 * fırlatılacak olan exception' dır.
 */
public class UserNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Benutzer mit gegebenem Email not gefunden";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
