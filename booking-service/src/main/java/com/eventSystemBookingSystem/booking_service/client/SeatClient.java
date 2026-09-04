package com.eventSystemBookingSystem.booking_service.client;

import com.eventSystemBookingSystem.booking_service.dto.SeatResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "seat-service")
public interface SeatClient {

    @GetMapping("/api/seats/{id}")
    SeatResponseDto getSeat(@PathVariable Long id);

    @PatchMapping("/api/seats/{id}")
    SeatResponseDto bookSeat(@PathVariable Long id);
}
