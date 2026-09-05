package com.eventSeatBookingSystem.seat_service.exception;

public class SeatAlreadyAvailableException extends RuntimeException{

    public SeatAlreadyAvailableException(String message) {
        super(message);
    }
}
