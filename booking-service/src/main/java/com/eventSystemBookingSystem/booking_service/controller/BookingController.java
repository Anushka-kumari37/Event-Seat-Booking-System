package com.eventSystemBookingSystem.booking_service.controller;


import com.eventSystemBookingSystem.booking_service.dto.BookingRequestDto;
import com.eventSystemBookingSystem.booking_service.dto.BookingResponseDto;
import com.eventSystemBookingSystem.booking_service.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
public class BookingController {

    private  final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        BookingResponseDto bookingResponseDto = bookingService.createBooking(bookingRequestDto);
        return ResponseEntity.ok(bookingResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        List<BookingResponseDto> bookingResponseDto = bookingService.getAllBooking();
        return ResponseEntity.ok(bookingResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long id) {
        BookingResponseDto bookingResponseDto = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingResponseDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDto>> getBookingByUserId(@PathVariable Long userId) {
        List<BookingResponseDto> bookingResponseDto = bookingService.getBookingByUserId(userId);
        return ResponseEntity.ok(bookingResponseDto);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<List<BookingResponseDto>> getBookingByEventId(@PathVariable Long eventId) {
        List<BookingResponseDto> bookingResponseDto = bookingService.getBookingsByEventId(eventId);
        return ResponseEntity.ok(bookingResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookingResponseDto>  cancelBooking(@PathVariable Long id,
                                                             @RequestBody BookingRequestDto bookingRequestDto) {
        BookingResponseDto bookingResponseDto = bookingService.cancelBooking(id,bookingRequestDto);
        return ResponseEntity.ok(bookingResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking Deleted successfully");

    }

}
