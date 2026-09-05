package com.eventSeatBookingSystem.notification_service.client;


import com.eventSeatBookingSystem.notification_service.config.FeignClientConfig;
import com.eventSeatBookingSystem.notification_service.dto.BookingResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service", configuration = FeignClientConfig.class)
public interface BookingClient {

    @GetMapping("/api/booking/{id}")
    BookingResponseDto getBooking(@PathVariable Long  id);
}
