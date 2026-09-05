package com.eventSeatBookingSystem.payment_service.dto;

import com.eventSeatBookingSystem.payment_service.entity.PaymentMethod;
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
public class PaymentRequestDto {

    @NotNull(message = "Booking id must required")
    private Long bookingId;
    @NotNull(message = "PaymentMethod must required")
    private PaymentMethod paymentMethod;
}
