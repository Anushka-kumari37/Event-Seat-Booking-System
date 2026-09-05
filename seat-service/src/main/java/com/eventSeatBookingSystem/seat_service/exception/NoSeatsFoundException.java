package com.eventSeatBookingSystem.seat_service.exception;

public class NoSeatsFoundException extends RuntimeException{

    public NoSeatsFoundException(String message) {
        super(message);
    }
}
