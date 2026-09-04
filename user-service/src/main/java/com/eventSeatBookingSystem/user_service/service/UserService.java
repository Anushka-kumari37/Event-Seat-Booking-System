package com.eventSeatBookingSystem.user_service.service;

import com.eventSeatBookingSystem.user_service.dto.UserRequestDto;
import com.eventSeatBookingSystem.user_service.dto.UserResponseDto;
import com.eventSeatBookingSystem.user_service.entity.Role;
import com.eventSeatBookingSystem.user_service.entity.User;
import com.eventSeatBookingSystem.user_service.exception.InvalidCredentialsException;
import com.eventSeatBookingSystem.user_service.exception.UserNotFoundException;
import com.eventSeatBookingSystem.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserResponseDto registerUser(@Valid UserRequestDto userRequestDto) {

        User user = new User();

        user.setName(userRequestDto.getName());
        user.setPassword(userRequestDto.getPassword());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNo(userRequestDto.getPhoneNo());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.USER);

        User u1 = userRepository.save(user);

        return ConverToDto(u1);
    }

    private UserResponseDto ConverToDto(User u1) {

        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setId(u1.getId());
        responseDto.setName(u1.getName());
        responseDto.setEmail(u1.getEmail());
        responseDto.setPhoneNo(u1.getPhoneNo());
        responseDto.setRole(u1.getRole());
        responseDto.setCreatedAt(u1.getCreatedAt());

        return responseDto;
    }

    public UserResponseDto loginUser(@Valid UserRequestDto requestDto) {

        User user = userRepository.findByName(requestDto.getName())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid username or password"
                        )
                );

        return null;
    }

    public List<UserResponseDto> getAllUser() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException(
                    "No users are available"
            );
        }

        return users.stream()
                .map(this::ConverToDto)
                .toList();
    }

    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );

        return ConverToDto(user);
    }

    public UserResponseDto updateUser(
            Long id,
            @Valid UserRequestDto userRequestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setPhoneNo(userRequestDto.getPhoneNo());

        User u1 = userRepository.save(user);

        return ConverToDto(u1);
    }

    public UserResponseDto patchUser(
            Long id,
            UserRequestDto userRequestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );

        if (userRequestDto.getName() != null) {
            user.setName(userRequestDto.getName());
        }

        if (userRequestDto.getPassword() != null) {
            user.setPassword(userRequestDto.getPassword());
        }

        if (userRequestDto.getEmail() != null) {
            user.setEmail(userRequestDto.getEmail());
        }

        if (userRequestDto.getPhoneNo() != null) {
            user.setPhoneNo(userRequestDto.getPhoneNo());
        }

        User updatedUser = userRepository.save(user);

        return ConverToDto(updatedUser);
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );

        userRepository.delete(user);
    }
}

