package com.eventSystemBookingSystem.booking_service.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // FEIGN EXCEPTIONS

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<String> handleFeignBadRequest(
            FeignException.BadRequest ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request sent to another service");
    }

    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<String> handleFeignUnauthorized(
            FeignException.Unauthorized ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized request to another service");
    }

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<String> handleFeignForbidden(
            FeignException.Forbidden ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied by another service");
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> handleFeignNotFound(
            FeignException.NotFound ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User, Event or Seat not found");
    }

    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<String> handleFeignConflict(
            FeignException.Conflict ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Conflict occurred in another service");
    }

    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<String> handleFeignInternalServerError(
            FeignException.InternalServerError ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error in another service");
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<String> handleFeignServiceUnavailable(
            FeignException.ServiceUnavailable ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("User, Event or Seat Service is currently unavailable");
    }


    // BOOKING EXCEPTIONS

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFound(
            BookingNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoBookingsFoundException.class)
    public ResponseEntity<String> handleNoBookingsFound(
            NoBookingsFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BookingAlreadyCancelledException.class)
    public ResponseEntity<String> handleBookingAlreadyCancelled(
            BookingAlreadyCancelledException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }


    //  SECURITY

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(
            AccessDeniedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied: You do not have permission to access this resource");
    }


    // VALIDATION

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(message);
    }


    // HTTP METHOD

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("HTTP method not allowed for this endpoint");
    }


    // \GENERAL EXCEPTION

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(
            Exception ex) {

        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error");
    }
}