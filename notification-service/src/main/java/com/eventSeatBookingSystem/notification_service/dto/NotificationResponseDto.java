package com.eventSeatBookingSystem.notification_service.dto;

import com.eventSeatBookingSystem.notification_service.entity.NotificationStatus;
import com.eventSeatBookingSystem.notification_service.entity.NotificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {

    private Long id;
    private Long userId;
    private Long bookingId;
    private String message;
    private NotificationType type;
    private LocalDateTime createdAt;
    private NotificationStatus status;
}
