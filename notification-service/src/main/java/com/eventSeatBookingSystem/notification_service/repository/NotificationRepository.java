package com.eventSeatBookingSystem.notification_service.repository;

import com.eventSeatBookingSystem.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUserId(Long userId);


    List<Notification> getByBookingId(Long bookingId);

}
