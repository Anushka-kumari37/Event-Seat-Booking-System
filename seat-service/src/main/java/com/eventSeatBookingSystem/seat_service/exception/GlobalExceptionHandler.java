package com.eventSeatBookingSystem.seat_service.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // FEIGN EXCEPTIONS

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(
            FeignException.BadRequest ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request sent to Event Service");
    }


    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<String> handleUnauthorized(
            FeignException.Unauthorized ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized request to Event Service");
    }


    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<String> handleForbidden(
            FeignException.Forbidden ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied by Event Service");
    }


    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> handleNotFound(
            FeignException.NotFound ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Event not found");
    }


    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<String> handleConflict(
            FeignException.Conflict ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Conflict occurred in Event Service");
    }


    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError(
            FeignException.InternalServerError ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Event Service internal server error");
    }


    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<String> handleServiceUnavailable(
            FeignException.ServiceUnavailable ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Event Service is currently unavailable");
    }

    // SEAT NOT FOUND

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> handleSeatNotFound(
            SeatNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // NO SEATS FOUND

    @ExceptionHandler(NoSeatsFoundException.class)
    public ResponseEntity<String> handleNoSeatsFound(
            NoSeatsFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // SEAT ALREADY BOOKED

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<String> handleSeatAlreadyBooked(
            SeatAlreadyBookedException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    //SEAT ALREADY AVAILABLE

    @ExceptionHandler(SeatAlreadyAvailableException.class)
    public ResponseEntity<String> handleSeatAlreadyAvailable(
            SeatAlreadyAvailableException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    // VALIDATION EXCEPTION

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(message);
    }

    // METHOD NOT ALLOWED

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("HTTP method not allowed for this endpoint");
    }

    // GENERAL EXCEPTION

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {

        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong ");
    }
}