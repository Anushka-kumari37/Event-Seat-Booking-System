package com.eventSeatBookingSystem.user_service.controller;

import com.eventSeatBookingSystem.user_service.dto.UserRequestDto;
import com.eventSeatBookingSystem.user_service.dto.UserResponseDto;
import com.eventSeatBookingSystem.user_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody
                                                            UserRequestDto userRequestDto){
        UserResponseDto response = userService.registerUser(userRequestDto);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> loginUser(@Valid @RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = userService.loginUser(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>>  getAllUser(){
        List<UserResponseDto> responseDto = userService.getAllUser();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        UserResponseDto responseDto = userService.getUserById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto > updateUser(@PathVariable Long id,
                                                       @Valid @RequestBody UserRequestDto userRequestDto){
        UserResponseDto responseDto = userService.updateUser(id, userRequestDto);
        return  ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patchUser(@PathVariable Long id,
                                                     @RequestBody UserRequestDto userRequestDto){
       UserResponseDto responseDto = userService.patchUser(id,userRequestDto);
       return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleteUser(@PathVariable Long id ){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
