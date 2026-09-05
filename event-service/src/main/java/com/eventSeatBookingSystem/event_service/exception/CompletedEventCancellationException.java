package com.eventSeatBookingSystem.event_service.exception;

public class CompletedEventCancellationException extends RuntimeException {

    public CompletedEventCancellationException(String message) {
        super(message);
    }
}
