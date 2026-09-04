package com.eventSeatBookingSystem.seat_service.client;

import com.eventSeatBookingSystem.seat_service.dto.EventResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "event-service")
public interface EventClient {

    @GetMapping("/api/events/{id}")
    EventResponseDto getEventId(@PathVariable("id") Long id);
}