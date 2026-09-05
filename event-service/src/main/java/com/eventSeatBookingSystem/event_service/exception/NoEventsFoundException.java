package com.eventSeatBookingSystem.event_service.exception;

public class NoEventsFoundException extends RuntimeException {

    public NoEventsFoundException(String message) {
        super(message);
    }
}
