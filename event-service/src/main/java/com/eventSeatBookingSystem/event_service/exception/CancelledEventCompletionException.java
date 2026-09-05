package com.eventSeatBookingSystem.event_service.exception;

public class CancelledEventCompletionException extends RuntimeException {

    public CancelledEventCompletionException(String message) {
        super(message);
    }
}
