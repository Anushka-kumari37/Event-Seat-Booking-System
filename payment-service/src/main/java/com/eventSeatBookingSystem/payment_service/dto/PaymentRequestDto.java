package com.eventSeatBookingSystem.payment_service.dto;

import com.eventSeatBookingSystem.payment_service.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    private Long bookingId;
    private PaymentMethod paymentMethod;
}
