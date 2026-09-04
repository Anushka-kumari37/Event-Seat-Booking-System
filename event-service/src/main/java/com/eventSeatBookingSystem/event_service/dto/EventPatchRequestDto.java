package com.eventSeatBookingSystem.event_service.dto;

import com.eventSeatBookingSystem.event_service.entity.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventPatchRequestDto {
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private Long totalSeats;
    private Long availableSeats;
    private Double price;
    private  Long userId;

}
