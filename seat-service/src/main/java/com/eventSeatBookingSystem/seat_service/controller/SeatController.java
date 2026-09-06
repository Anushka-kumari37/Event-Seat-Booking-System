package com.eventSeatBookingSystem.seat_service.controller;

import com.eventSeatBookingSystem.seat_service.dto.SeatRequestDto;
import com.eventSeatBookingSystem.seat_service.dto.SeatResponseDto;
import com.eventSeatBookingSystem.seat_service.entity.Seat;
import com.eventSeatBookingSystem.seat_service.service.SeatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    @Value("${server.port}")
     private String port;
    private  final SeatService seatService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SeatResponseDto> createSeat(@Valid @RequestBody SeatRequestDto seatRequestDto){
        SeatResponseDto seatResponseDto = seatService.createSeat(seatRequestDto);
        return ResponseEntity.ok().body(seatResponseDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<SeatResponseDto>> getSeats(){
        List<SeatResponseDto> responseDto = seatService.getSeats();
        return  ResponseEntity.ok(responseDto);
    }
    @GetMapping("/instance")
    public ResponseEntity<String> getInstance() {

        return ResponseEntity.ok(
                "Request handled by Seat Service running on port: " + port
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SeatResponseDto> getSeatById(@PathVariable Long id){
        SeatResponseDto responseDto = seatService.getSeatById(id);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SeatResponseDto>  updateSeat(@PathVariable Long id,
                                                       @RequestBody SeatRequestDto seatRequestDto){
        SeatResponseDto responseDto = seatService.updateSeat(id,seatRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id){
        seatService.deleteById(id);
        return ResponseEntity.ok("Seat Deleted successfully");
    }

    @GetMapping("/event/{eventId}/available")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<SeatResponseDto>> getAvailableSeats(@PathVariable Long eventId){
        List<SeatResponseDto>  responseDto = seatService.getAvailableSeats(eventId);
        return  ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}/book")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public  ResponseEntity<SeatResponseDto> bookSeat(@PathVariable Long id){
        SeatResponseDto responseDto = seatService.bookSeat(id);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}/release")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SeatResponseDto> releaseSeat(@PathVariable Long id){
        SeatResponseDto responseDto = seatService.releaseSeat(id);
        return ResponseEntity.ok(responseDto);
    }
}
