package com.eventSeatBookingSystem.seat_service.dto;

import com.eventSeatBookingSystem.seat_service.entity.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequestDto {

    private  Long eventId;
    private String seatNumber;
    private SeatType seatType;
    private  Double price;


}
