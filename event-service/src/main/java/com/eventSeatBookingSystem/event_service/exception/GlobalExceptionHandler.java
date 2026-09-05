package com.eventSeatBookingSystem.event_service.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // FEIGN EXCEPTIONS

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<String> handleFeignBadRequest(FeignException.BadRequest ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request sent to User Service");
    }

    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<String> handleFeignUnauthorized(FeignException.Unauthorized ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Unauthorized request to User Service");
    }

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<String> handleFeignForbidden(FeignException.Forbidden ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied by User Service");
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> handleFeignNotFound(FeignException.NotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<String> handleFeignConflict(FeignException.Conflict ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Conflict occurred in User Service");
    }

    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<String> handleFeignInternalServerError(
            FeignException.InternalServerError ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error in User Service");
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<String> handleFeignServiceUnavailable(
            FeignException.ServiceUnavailable ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("User Service is currently unavailable");
    }


    // EVENT EXCEPTIONS

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleEventNotFound(
            EventNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoEventsFoundException.class)
    public ResponseEntity<String> handleNoEventsFound(
            NoEventsFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedEventException.class)
    public ResponseEntity<String> handleUnauthorizedEvent(
            UnauthorizedEventException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(
            AccessDeniedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("You are not authorized");
    }

    @ExceptionHandler(EventAlreadyCancelledException.class)
    public ResponseEntity<String> handleEventAlreadyCancelled(
            EventAlreadyCancelledException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EventAlreadyCompletedException.class)
    public ResponseEntity<String> handleEventAlreadyCompleted(
            EventAlreadyCompletedException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CompletedEventCancellationException.class)
    public ResponseEntity<String> handleCompletedEventCancellation(
            CompletedEventCancellationException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CancelledEventCompletionException.class)
    public ResponseEntity<String> handleCancelledEventCompletion(
            CancelledEventCompletionException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }


    //SECURITY

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<String> handleAccessDenied(
//            AccessDeniedException ex) {
//
//        return ResponseEntity
//                .status(HttpStatus.FORBIDDEN)
//                .body("Access denied: You do not have permission to access this resource");
//    }


    // VALIDATION

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .badRequest()
                .body(errors);
    }


    // HTTP METHOD

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(
            HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("HTTP method not allowed for this endpoint");
    }


    //  GENERAL EXCEPTION

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {

        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error");
    }
}
