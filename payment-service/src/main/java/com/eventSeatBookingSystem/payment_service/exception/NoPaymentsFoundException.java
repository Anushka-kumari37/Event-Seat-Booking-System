package com.eventSeatBookingSystem.payment_service.exception;

public class NoPaymentsFoundException extends RuntimeException{

    public NoPaymentsFoundException(String message){
        super(message);
    }
}
