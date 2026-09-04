package com.eventSeatBookingSystem.event_service.Client;

import com.eventSeatBookingSystem.event_service.dto.UserResponseDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
public interface  UserClient {

    @GetMapping("/api/user/{id}")
    UserResponseDto getUserById(@PathVariable Long id);
}
