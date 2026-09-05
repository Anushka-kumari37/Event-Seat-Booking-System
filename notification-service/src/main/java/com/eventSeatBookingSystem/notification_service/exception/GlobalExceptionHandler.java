

package com.eventSeatBookingSystem.notification_service.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // NOTIFICATION NOT FOUND
    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<String> handleNotificationNotFound(
            NotificationNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    //UNAUTHORIZED ACCESS
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(
            AccessDeniedException exception) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", HttpStatus.FORBIDDEN.value());
        response.put("error", "Forbidden");
        response.put("message", "You are not authorized");

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    // NO NOTIFICATIONS FOUND

    @ExceptionHandler(NoNotificationsFoundException.class)
    public ResponseEntity<String> handleNoNotificationsFound(
            NoNotificationsFoundException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // VALIDATION ERROR

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    // FEIGN 400

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<Map<String, Object>> handleFeignBadRequest(
            FeignException.BadRequest exception) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Bad Request",
                "Invalid request sent to Booking Service"
        );
    }

    // FEIGN 401
    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<Map<String, Object>> handleFeignUnauthorized(
            FeignException.Unauthorized exception) {

        return buildResponse(
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                "Authentication failed while communicating with Booking Service"
        );
    }

    // FEIGN 403
    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<Map<String, Object>> handleFeignForbidden(
            FeignException.Forbidden exception) {

        return buildResponse(
                HttpStatus.FORBIDDEN,
                "Forbidden",
                "Access denied by Booking Service"
        );
    }

    // FEIGN 404
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Map<String, Object>> handleFeignNotFound(
            FeignException.NotFound exception) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                "Booking Not Found",
                "The requested booking does not exist"
        );
    }
    // FEIGN 409

    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<Map<String, Object>> handleFeignConflict(
            FeignException.Conflict exception) {

        return buildResponse(
                HttpStatus.CONFLICT,
                "Conflict",
                "A conflict occurred in Booking Service"
        );
    }

    // FEIGN 500
    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<Map<String, Object>> handleFeignInternalServerError(
            FeignException.InternalServerError exception) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                "Booking Service encountered an internal error"
        );
    }
    // FEIGN 503

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<Map<String, Object>> handleFeignServiceUnavailable(
            FeignException.ServiceUnavailable exception) {

        return buildResponse(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Service Unavailable",
                "Booking Service is currently unavailable"
        );
    }

    // OTHER FEIGN ERRORS
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignException(
            FeignException exception) {

        return buildResponse(
                HttpStatus.BAD_GATEWAY,
                "Booking Service Communication Error",
                "Unable to communicate with Booking Service"
        );
    }

    // GENERIC EXCEPTION
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(
            Exception exception) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                exception.getMessage()
        );
    }

    // COMMON RESPONSE METHOD

    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status,
            String error,
            String message) {

        Map<String, Object> response = new HashMap<>();

        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", error);
        response.put("message", message);

        return new ResponseEntity<>(
                response,
                status
        );
    }
}