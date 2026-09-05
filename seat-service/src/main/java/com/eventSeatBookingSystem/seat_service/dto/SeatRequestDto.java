package com.eventSeatBookingSystem.seat_service.dto;

import com.eventSeatBookingSystem.seat_service.entity.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Event id must required")
    private  Long eventId;
    @NotBlank(message = "Seat number must required")
    private String seatNumber;
    @NotNull(message = "Seat Type is must required")
    private SeatType seatType;
    @NotNull (message =  "Seat Price is required")
    private  Double price;


}
