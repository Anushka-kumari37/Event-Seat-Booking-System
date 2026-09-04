package com.eventSeatBookingSystem.user_service.dto;

import com.eventSeatBookingSystem.user_service.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
}
