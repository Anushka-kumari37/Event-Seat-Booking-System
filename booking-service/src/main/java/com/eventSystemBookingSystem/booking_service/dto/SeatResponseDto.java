package com.eventSystemBookingSystem.booking_service.dto;

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
    private String  seatType;
    private Double price;
    private String  status;
    private  Long evenId;

}
