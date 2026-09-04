package com.eventSystemBookingSystem.booking_service.dto;

import com.eventSystemBookingSystem.booking_service.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingResponseDto {

    private Long id;
    private  Long userId;
    private  Long eventId;
    private  Long seatId;
    private LocalDateTime bookingDate;
    private Double totalAmount;
    private BookingStatus bookingStatus;
}
