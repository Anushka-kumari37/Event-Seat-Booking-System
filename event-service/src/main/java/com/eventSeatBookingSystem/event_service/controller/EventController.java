package com.eventSeatBookingSystem.event_service.controller;

import com.eventSeatBookingSystem.event_service.dto.EventPatchRequestDto;
import com.eventSeatBookingSystem.event_service.dto.EventRequestDto;
import com.eventSeatBookingSystem.event_service.dto.EventResponseDto;
import com.eventSeatBookingSystem.event_service.service.EventService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> createEvent(@Valid @RequestBody EventRequestDto requestDto){
        EventResponseDto responseDto = eventService.createEvent(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getAllEvents(){
        List<EventResponseDto> responseDto = eventService.getAllEvents();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EventResponseDto> getById(@PathVariable Long id){
        EventResponseDto responseDto = eventService.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getByName(@PathVariable String name){
        List<EventResponseDto> responseDto = eventService.getByName(name);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id,@RequestParam Long userId, @Valid
                                                         @RequestBody EventRequestDto requestDto){
        EventResponseDto responseDto = eventService.updateEvent(id, userId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> updateById(@PathVariable Long id, @RequestParam Long userId,@Valid
                                                       @RequestBody EventPatchRequestDto requestDto){
        EventResponseDto responseDto = eventService.updateById(id,userId,requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String > deleteById(@PathVariable Long id){
         eventService.deleteById(id);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @GetMapping("/location/{location}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getByLocation(@PathVariable String location){
        List<EventResponseDto> responseDto = eventService.getByLocation(location);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/upcoming")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getUpcomingEvents(){
        List<EventResponseDto> responseDto = eventService.getUpcomingEvents();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/price")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getByPriceBetween(@RequestParam Double min,
                                                                    @RequestParam Double max){
        List<EventResponseDto> responseDto = eventService.getByPriceRange(min,max);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EventResponseDto>> getEventByDate(@PathVariable LocalDate date){
        List<EventResponseDto> responseDto = eventService.getEventsByDate(date);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/cancel/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> cancelEvent(@PathVariable Long id,
                                                        @RequestParam Long userId){
        EventResponseDto responseDto = eventService.cancelEvent(id,userId);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}/complete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDto> completeEvent(
            @PathVariable Long id,
            @RequestParam Long userId) {

        return ResponseEntity.ok(
                eventService.completeEvent(id, userId)
        );
    }
}
