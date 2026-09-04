package com.eventSeatBookingSystem.payment_service.repository;

import com.eventSeatBookingSystem.payment_service.entity.Payment;
import com.eventSeatBookingSystem.payment_service.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    List<Payment> findByBookingId(Long bookingId);

    List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);
    
}
