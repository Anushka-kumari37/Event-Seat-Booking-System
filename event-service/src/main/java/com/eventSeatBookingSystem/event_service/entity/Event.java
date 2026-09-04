package com.eventSeatBookingSystem.event_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private Long totalSeats;
    private Long availableSeats;
    private Double price;
    private Long createdBy;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

}
