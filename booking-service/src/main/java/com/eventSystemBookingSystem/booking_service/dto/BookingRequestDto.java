package com.eventSystemBookingSystem.booking_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    @NotNull(message= "User id is required")
    private  Long userId;
    @NotNull(message = "EventId is must required")
    private  Long eventId;
    @NotNull(message = "Seat id is must required")
    private Long seatId;

}
