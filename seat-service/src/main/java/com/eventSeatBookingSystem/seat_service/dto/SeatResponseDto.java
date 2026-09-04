package com.eventSeatBookingSystem.seat_service.dto;

import com.eventSeatBookingSystem.seat_service.entity.SeatType;
import com.eventSeatBookingSystem.seat_service.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponseDto {

    private Long id;
    private String seatNumber;
    private SeatType seatType;
    private Double price;
    private Status status;
    private  Long evenId;

}
