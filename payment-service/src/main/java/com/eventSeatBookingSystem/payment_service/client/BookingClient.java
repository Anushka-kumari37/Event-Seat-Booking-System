package com.eventSeatBookingSystem.payment_service.client;

import com.eventSeatBookingSystem.payment_service.config.FeignClientConfig;
import com.eventSeatBookingSystem.payment_service.dto.BookingResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service", configuration = FeignClientConfig.class)
public interface BookingClient {

    @GetMapping("/api/booking/{id}")
    BookingResponseDto getById(@PathVariable Long id);
}
