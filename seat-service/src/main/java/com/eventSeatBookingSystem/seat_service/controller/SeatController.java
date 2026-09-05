package com.eventSeatBookingSystem.seat_service.controller;

import com.eventSeatBookingSystem.seat_service.dto.SeatRequestDto;
import com.eventSeatBookingSystem.seat_service.dto.SeatResponseDto;
import com.eventSeatBookingSystem.seat_service.entity.Seat;
import com.eventSeatBookingSystem.seat_service.service.SeatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@AllArgsConstructor
public class SeatController {

    private SeatService seatService;

    @PostMapping
    public ResponseEntity<SeatResponseDto> createSeat(@Valid @RequestBody SeatRequestDto seatRequestDto){
        SeatResponseDto seatResponseDto = seatService.createSeat(seatRequestDto);
        return ResponseEntity.ok().body(seatResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<SeatResponseDto>> getSeats(){
        List<SeatResponseDto> responseDto = seatService.getSeats();
        return  ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponseDto> getSeatById(@PathVariable Long id){
        SeatResponseDto responseDto = seatService.getSeatById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatResponseDto>  updateSeat(@PathVariable Long id,
                                                       @RequestBody SeatRequestDto seatRequestDto){
        SeatResponseDto responseDto = seatService.updateSeat(id,seatRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id){
        seatService.deleteById(id);
        return ResponseEntity.ok("Seat Deleted successfully");
    }

    @GetMapping("/event/{eventId}/available")
    public ResponseEntity<List<SeatResponseDto>> getAvailableSeats(@PathVariable Long eventId){
        List<SeatResponseDto>  responseDto = seatService.getAvailableSeats(eventId);
        return  ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}/book")
    public  ResponseEntity<SeatResponseDto> bookSeat(@PathVariable Long id){
        SeatResponseDto responseDto = seatService.bookSeat(id);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}/release")
    public ResponseEntity<SeatResponseDto> releaseSeat(@PathVariable Long id){
        SeatResponseDto responseDto = seatService.releaseSeat(id);
        return ResponseEntity.ok(responseDto);
    }
}
