package com.eventSeatBookingSystem.user_service.exception;

public class UserEmailAlreadyExists extends RuntimeException{

    public UserEmailAlreadyExists(String message){
        super(message);
    }
}
