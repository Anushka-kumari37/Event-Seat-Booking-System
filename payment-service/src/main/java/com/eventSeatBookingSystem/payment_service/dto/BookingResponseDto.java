package com.eventSeatBookingSystem.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private Long id;
    private  Long userId;
    private  Long eventId;
    private  Long seatId;
    private LocalDateTime bookingDate;
    private Double totalAmount;
    private String  bookingStatus;
}
