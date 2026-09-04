package com.eventSeatBookingSystem.seat_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
     private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private Long totalSeats;
    private Long availableSeats;
    private Double price;
    private  Long userId;


}
