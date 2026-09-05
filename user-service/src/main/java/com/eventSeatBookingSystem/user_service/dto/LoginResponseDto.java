package com.eventSeatBookingSystem.user_service.dto;

import com.eventSeatBookingSystem.user_service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    private Long id;
    private String name;
    private String email;
    private Role role;

}
