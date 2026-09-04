package com.eventSeatBookingSystem.payment_service.dto;

import com.eventSeatBookingSystem.payment_service.entity.PaymentMethod;
import com.eventSeatBookingSystem.payment_service.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {

    private Long paymentId;
    private Long bookingId;
    private Long userId;
    private Double amount;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
}
