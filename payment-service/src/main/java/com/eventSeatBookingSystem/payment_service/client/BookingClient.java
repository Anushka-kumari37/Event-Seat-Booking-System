package com.eventSeatBookingSystem.payment_service.client;

import com.eventSeatBookingSystem.payment_service.dto.BookingResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service")
public interface BookingClient {

    @GetMapping("/api/booking")
    BookingResponseDto getById(@PathVariable Long id);
}
