package com.eventSystemBookingSystem.booking_service.client;

import com.eventSystemBookingSystem.booking_service.dto.SeatResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "seat-service")
public interface SeatClient {

    @GetMapping("/api/seats/{id}")
    SeatResponseDto getSeat(@PathVariable Long id);

    @PutMapping("/api/seats/{id}/book")
    SeatResponseDto bookSeat(@PathVariable("id") Long id);
}
