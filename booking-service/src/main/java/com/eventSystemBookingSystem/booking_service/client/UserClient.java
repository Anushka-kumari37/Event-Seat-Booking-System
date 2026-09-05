package com.eventSystemBookingSystem.booking_service.client;

import com.eventSystemBookingSystem.booking_service.config.FeignClientConfig;
import com.eventSystemBookingSystem.booking_service.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", configuration = FeignClientConfig.class)
public interface UserClient {

    @GetMapping("/api/user/{id}")
    UserResponseDto getUser(@PathVariable Long id);
}
