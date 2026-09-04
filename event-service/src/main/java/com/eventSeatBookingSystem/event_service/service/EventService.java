package com.eventSeatBookingSystem.event_service.service;

import com.eventSeatBookingSystem.event_service.Client.UserClient;
import com.eventSeatBookingSystem.event_service.dto.EventPatchRequestDto;
import com.eventSeatBookingSystem.event_service.dto.EventRequestDto;
import com.eventSeatBookingSystem.event_service.dto.EventResponseDto;
import com.eventSeatBookingSystem.event_service.dto.UserResponseDto;
import com.eventSeatBookingSystem.event_service.entity.Event;
import com.eventSeatBookingSystem.event_service.entity.EventStatus;
import com.eventSeatBookingSystem.event_service.repository.EventRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private  final UserClient userClient;

    public EventResponseDto createEvent(@Valid EventRequestDto requestDto) {

        UserResponseDto user = userClient.getUserById(requestDto.getUserId());
        Event event = new Event();
        event.setName(requestDto.getName());
        event.setLocation(requestDto.getLocation());
        event.setPrice(requestDto.getPrice());
        event.setDate(requestDto.getDate());
        event.setDescription(requestDto.getDescription());
        event.setTotalSeats(requestDto.getTotalSeats());
        event.setAvailableSeats(requestDto.getAvailableSeats());
        event.setCreatedBy(user.getId());
        event.setStatus(EventStatus.UPCOMING);

        Event event1 = eventRepository.save(event);
        return ConvertToDto(event1);
    }

    private EventResponseDto ConvertToDto(Event event1) {
        EventResponseDto responseDto = new EventResponseDto();
        responseDto.setId(event1.getId());
        responseDto.setName(event1.getName());
        responseDto.setDate(event1.getDate());
        responseDto.setLocation(event1.getLocation());
        responseDto.setDescription(event1.getDescription());
        responseDto.setPrice(event1.getPrice());
        responseDto.setAvailableSeats(event1.getAvailableSeats());
        responseDto.setTotalSeats(event1.getTotalSeats());
        responseDto.setStatus(event1.getStatus());
        return  responseDto;
    }

    public List<EventResponseDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()){
            throw new RuntimeException("Events are not available");
        }
        return events.stream().map(this::ConvertToDto).toList();
    }


    public EventResponseDto getById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Event is not found with this id"));
        return ConvertToDto(event);

    }

    public List<EventResponseDto> getByName(String name) {
        List<Event> events = eventRepository.findByName(name);
        if(events.isEmpty()){
            throw new RuntimeException(name + " Events are not available");
        }
        return events.stream().map(this::ConvertToDto).toList();
    }

    public EventResponseDto updateEvent(Long id, Long userId, EventRequestDto requestDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Event is not available with this id"));
        if (!event.getCreatedBy().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this event");
        }
        event.setName(requestDto.getName());
        event.setLocation(requestDto.getLocation());
        event.setDescription(requestDto.getDescription());
        event.setDate(requestDto.getDate());
        event.setPrice(requestDto.getPrice());
        event.setAvailableSeats(requestDto.getAvailableSeats());
        event.setTotalSeats(requestDto.getTotalSeats());

        Event updatedEvent = eventRepository.save(event);
        return ConvertToDto(updatedEvent);
    }

    public EventResponseDto updateById(Long id,Long userId, EventPatchRequestDto requestDto) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Event is not available for this id"));

        if (!event.getCreatedBy().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this event");
        }

        if(requestDto.getName()!=null){
            event.setName(requestDto.getName());
        }

        if(requestDto.getDate()!=null){
            event.setDate(requestDto.getDate());
        }
        if(requestDto.getLocation()!= null){
            event.setLocation(requestDto.getLocation());
        }
        if(requestDto.getAvailableSeats()!=null){
            event.setAvailableSeats(requestDto.getAvailableSeats());
        }
        if(requestDto.getTotalSeats()!=null){
            event.setTotalSeats(requestDto.getTotalSeats());
        }
        if(requestDto.getPrice()!= null){
            event.setPrice(requestDto.getPrice());
        }
        if(requestDto.getDescription()!= null){
            event.setDescription(requestDto.getDescription());
        }
        Event event1 = eventRepository.save(event);
        return ConvertToDto(event1);

    }

    public void deleteById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Event is not available"));
        eventRepository.deleteById(id);
    }

    public List<EventResponseDto> getByLocation(String location){
        List<Event> event = eventRepository.findByLocation(location);
        if (event.isEmpty()){
            throw new RuntimeException("Events are not available for this location");
        }
        return event.stream().map(this::ConvertToDto).toList();
    }

    public List<EventResponseDto> getUpcomingEvents() {
        List<Event> events = eventRepository.findByDateAfter(LocalDate.now());
        return events.stream().map(this::ConvertToDto).toList();
    }

    public List<EventResponseDto> getEventsByDate(LocalDate date){
        List<Event> events = eventRepository.findByDate(date);
        return events.stream().map(this::ConvertToDto).toList();
    }

    public List<EventResponseDto> getByPriceRange(Double min, Double max) {
        List<Event> event = eventRepository.findByPriceBetween(min,max);
        return event.stream().map(this::ConvertToDto).toList();
    }

    public EventResponseDto cancelEvent(Long id,Long userId) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Event is not present with this id "));

        if (!event.getCreatedBy().equals(userId)) {
            throw new RuntimeException(
                    "You are not authorized to cancel this event");
        }
        if(event.getStatus()== EventStatus.CANCELLED){
            throw new RuntimeException("Event is already cancelled");
        }
        if(event.getStatus()==EventStatus.COMPLETED){
            throw  new RuntimeException("Completed event can not be cancelled");
        }

        event.setStatus(EventStatus.CANCELLED);
        Event event1 = eventRepository.save(event);
        return  ConvertToDto(event1);
    }

    public EventResponseDto completeEvent(Long id, Long userId) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Event is not present with this id"));


        if (!event.getCreatedBy().equals(userId)) {
            throw new RuntimeException(
                    "You are not authorized to complete this event");
        }

        if (event.getStatus() == EventStatus.COMPLETED) {
            throw new RuntimeException("Event is already completed");
        }

        if (event.getStatus() == EventStatus.CANCELLED) {
            throw new RuntimeException(
                    "Cancelled event cannot be marked as completed");
        }

        event.setStatus(EventStatus.COMPLETED);

        Event updatedEvent = eventRepository.save(event);

        return ConvertToDto(updatedEvent);
    }
}
