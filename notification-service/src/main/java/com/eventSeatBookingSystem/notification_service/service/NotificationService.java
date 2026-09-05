package com.eventSeatBookingSystem.notification_service.service;

import com.eventSeatBookingSystem.notification_service.client.BookingClient;
import com.eventSeatBookingSystem.notification_service.dto.BookingResponseDto;
import com.eventSeatBookingSystem.notification_service.dto.NotificationRequestDto;
import com.eventSeatBookingSystem.notification_service.dto.NotificationResponseDto;
import com.eventSeatBookingSystem.notification_service.entity.Notification;
import com.eventSeatBookingSystem.notification_service.entity.NotificationStatus;
import com.eventSeatBookingSystem.notification_service.exception.NoNotificationsFoundException;
import com.eventSeatBookingSystem.notification_service.exception.NotificationNotFoundException;
import com.eventSeatBookingSystem.notification_service.repository.NotificationRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final BookingClient bookingClient;


    public NotificationResponseDto createNotification(@Valid NotificationRequestDto notificationRequestDto) {
        BookingResponseDto booking = bookingClient.getBooking(
                notificationRequestDto.getBookingId());
        Notification notification = new Notification();
        notification.setBookingId(booking.getId());
        notification.setUserId(booking.getUserId());
        notification.setType(notificationRequestDto.getNotificationType());
        notification.setMessage(notificationRequestDto.getMessage());
        notification.setStatus(NotificationStatus.UNREAD);
        notification.setCreatedAt(LocalDateTime.now());

        Notification notification1 = notificationRepository.save(notification);
        return ConvertToDto(notification1);
    }

    private NotificationResponseDto ConvertToDto(Notification notification1) {
        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();
        notificationResponseDto.setId(notification1.getId());
        notificationResponseDto.setUserId(notification1.getUserId());
        notificationResponseDto.setBookingId(notification1.getBookingId());
        notificationResponseDto.setMessage(notification1.getMessage());
        notificationResponseDto.setStatus(notification1.getStatus());
        notificationResponseDto.setCreatedAt(notification1.getCreatedAt());
        notificationResponseDto.setType(notification1.getType());
        return notificationResponseDto;
    }

    public List<NotificationResponseDto> getAllNotification() {
        List<Notification> notifications = notificationRepository.findAll();
        if (notifications.isEmpty()) {
            throw  new NoNotificationsFoundException("No notifications found");
        }
        return notifications.stream().map(this::ConvertToDto).toList();
    }


    public NotificationResponseDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("No notification found with id: " + id));
        return ConvertToDto(notification);
    }

    public List<NotificationResponseDto> getNotificationByUser(Long userId) {
        List<Notification> notification = notificationRepository.findByUserId(userId);
        if (notification.isEmpty()) {
            throw  new NoNotificationsFoundException("No notifications found");
        }
        return notification.stream().map(this::ConvertToDto).toList();
    }

    public List<NotificationResponseDto> getNotificationByBooking(Long bookingId) {
        List<Notification> notifications = notificationRepository.getByBookingId(bookingId);
        if (notifications.isEmpty()) {
            throw  new NoNotificationsFoundException("No notifications found with this booking id "+bookingId);
        }
        return notifications.stream().map(this::ConvertToDto).toList();
    }

    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("No notification found with id: " + id));
        notificationRepository.delete(notification);
    }

    public NotificationResponseDto markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException("No notification found with id: " + id));
        notification.setStatus(NotificationStatus.READ);
        Notification notification1 = notificationRepository.save(notification);
        return ConvertToDto(notification1);
    }
}
