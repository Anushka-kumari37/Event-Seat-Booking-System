package com.eventSystemBookingSystem.booking_service.client;

import com.eventSystemBookingSystem.booking_service.config.FeignClientConfig;
import com.eventSystemBookingSystem.booking_service.dto.EventResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service", configuration = FeignClientConfig.class )
public interface EventClient {

    @GetMapping("/api/events/{id}")
    EventResponseDto getEvent(@PathVariable Long id);
}
