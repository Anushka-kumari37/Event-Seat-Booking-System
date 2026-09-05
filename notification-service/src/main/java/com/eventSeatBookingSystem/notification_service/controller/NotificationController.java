package com.eventSeatBookingSystem.notification_service.controller;

import com.eventSeatBookingSystem.notification_service.dto.NotificationRequestDto;
import com.eventSeatBookingSystem.notification_service.dto.NotificationResponseDto;
import com.eventSeatBookingSystem.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NotificationResponseDto> createNotification(@Valid @RequestBody NotificationRequestDto
                                                                                  notificationRequestDto) {
        NotificationResponseDto notificationResponseDto = notificationService
                .createNotification(notificationRequestDto);
        return ResponseEntity.ok(notificationResponseDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotifications() {
        List<NotificationResponseDto> notificationResponseDto = notificationService.getAllNotification();
        return ResponseEntity.ok(notificationResponseDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<NotificationResponseDto> getNotification(@PathVariable Long id) {
        NotificationResponseDto responseDto = notificationService.getNotificationById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationResponseDto> notificationResponseDto =
                notificationService.getNotificationByUser(userId);
        return ResponseEntity.ok(notificationResponseDto);
    }

    @GetMapping("/booking/{bookingId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsByBookingId
            (@PathVariable Long bookingId) {
        List<NotificationResponseDto> notificationResponseDto =
                notificationService.getNotificationByBooking(bookingId);
        return ResponseEntity.ok(notificationResponseDto);
    }

    @PatchMapping("/{id}/read")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<NotificationResponseDto> readNotification(@PathVariable Long id) {
        NotificationResponseDto responseDto = notificationService.markAsRead(id);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok("Notification deleted successfully");
    }
}
