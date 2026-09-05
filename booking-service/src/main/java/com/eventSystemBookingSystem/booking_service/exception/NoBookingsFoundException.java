package com.eventSystemBookingSystem.booking_service.exception;

public class NoBookingsFoundException extends RuntimeException{

    public NoBookingsFoundException(String message) {
        super(message);
    }
}
