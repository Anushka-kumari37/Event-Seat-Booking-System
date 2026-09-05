package com.eventSeatBookingSystem.notification_service.dto;

import com.eventSeatBookingSystem.notification_service.entity.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {


    @NotNull(message = "Booking id is required")
    private Long bookingId;
    @NotNull(message = "Notification type is required")
    private NotificationType notificationType;

    @NotBlank(message = "Message is required")
    private String message;
}
