package com.eventSystemBookingSystem.booking_service.exception;

public class BookingAlreadyCancelledException extends RuntimeException{

    public BookingAlreadyCancelledException(String message) {
        super(message);
    }
}
