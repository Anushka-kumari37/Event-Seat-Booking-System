package com.eventSeatBookingSystem.payment_service.exception;


import com.eventSeatBookingSystem.payment_service.entity.PaymentMethod;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.resource.NoResourceFoundException;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(
            FeignException.BadRequest ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request sent to Booking Service"+ex.getMessage());
    }


    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<String> handleUnauthorized(
            FeignException.Unauthorized ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized request to Booking Service");
    }


    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<String> handleForbidden(
            FeignException.Forbidden ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied by Booking Service");
    }


    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> handleNotFound(
            FeignException.NotFound ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Booking not found");
    }


    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<String> handleConflict(
            FeignException.Conflict ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Conflict occurred in Booking Service");
    }


    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError(
            FeignException.InternalServerError ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Booking Service internal server error");
    }


    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<String> handleServiceUnavailable(
            FeignException.ServiceUnavailable ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Booking Service is currently unavailable");
    }

    // PAYMENT NOT FOUND

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<String> handlePaymentNotFound(
            PaymentNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // NO PAYMENTS FOUND

    @ExceptionHandler(NoPaymentsFoundException.class)
    public ResponseEntity<String> handleNoPaymentsFound(
            NoPaymentsFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // VALIDATION EXCEPTION

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity .status(HttpStatus.BAD_REQUEST)
                .body(errors);
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
    public ResponseEntity<String> handleGeneralException(
            Exception ex) {

        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");

    }
//Invalid input

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {

        Throwable cause = ex;

        while (cause != null) {

            if (cause instanceof InvalidFormatException invalidFormatException) {

                String invalidValue =
                        String.valueOf(invalidFormatException.getValue());

                if (invalidFormatException.getTargetType()
                        .equals(PaymentMethod.class)) {

                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST).body(
                                    invalidValue +
                                            " is not one of the expected values for PaymentMethod. " +
                                            "Expected values are: CARD, NET_BANKING, UPI");
                    }
            }

            cause = cause.getCause();
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request body");
    }
    //Unauthorized access
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(
            AccessDeniedException exception) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", HttpStatus.FORBIDDEN.value());
        response.put("error", "Forbidden");
        response.put("message", "You are not authorized");

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    //No resource found exception
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleNoResourceFound(
            NoResourceFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Endpoint not found");
    }
}
