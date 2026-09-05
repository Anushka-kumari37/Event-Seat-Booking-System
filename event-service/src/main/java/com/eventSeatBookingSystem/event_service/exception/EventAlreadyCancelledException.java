package com.eventSeatBookingSystem.event_service.exception;

public class EventAlreadyCancelledException extends RuntimeException{

    public EventAlreadyCancelledException(String message) {
        super(message);
    }
}
