package com.eventSystemBookingSystem.booking_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  Long userId;
    private  Long eventId;
    private Long seatId;
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus  bookingStatus;
    private Double totalAmount;


}
