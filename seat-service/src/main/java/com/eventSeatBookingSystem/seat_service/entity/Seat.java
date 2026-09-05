package com.eventSeatBookingSystem.seat_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Long eventId;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private  Double price;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String seatNumber;
}
