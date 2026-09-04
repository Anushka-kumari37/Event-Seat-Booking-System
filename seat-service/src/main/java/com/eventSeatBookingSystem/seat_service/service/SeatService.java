package com.eventSeatBookingSystem.seat_service.service;

import com.eventSeatBookingSystem.seat_service.client.EventClient;
import com.eventSeatBookingSystem.seat_service.dto.EventResponseDto;
import com.eventSeatBookingSystem.seat_service.dto.SeatRequestDto;
import com.eventSeatBookingSystem.seat_service.dto.SeatResponseDto;
import com.eventSeatBookingSystem.seat_service.entity.Seat;
import com.eventSeatBookingSystem.seat_service.entity.Status;
import com.eventSeatBookingSystem.seat_service.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final EventClient eventClient;

    public SeatResponseDto createSeat(SeatRequestDto seatRequestDto) {

        EventResponseDto event = eventClient.getEventId(seatRequestDto.getEventId());
        Seat seat = new Seat();
        seat.setEventId(event.getId());
        seat.setSeatNumber(seatRequestDto.getSeatNumber());
        seat.setPrice(seatRequestDto.getPrice());
        seat.setSeatType(seatRequestDto.getSeatType());
        seat.setStatus(Status.AVAILABLE);

        Seat seat1 = seatRepository.save(seat);
        return ConvertToDto(seat1);

    }

    private SeatResponseDto ConvertToDto(Seat seat1) {
        SeatResponseDto seatResponseDto = new SeatResponseDto();
        seatResponseDto.setId(seat1.getId());
        seatResponseDto.setSeatNumber(seat1.getSeatNumber());
        seatResponseDto.setPrice(seat1.getPrice());
        seatResponseDto.setSeatType(seat1.getSeatType());
        seatResponseDto.setEvenId(seat1.getEventId());
        seatResponseDto.setStatus(seat1.getStatus());
        return seatResponseDto;
    }

    public List<SeatResponseDto> getSeats() {
       List<Seat> seat = seatRepository.findAll();
        if(seat.isEmpty()){
            throw new RuntimeException("Seats are empty");
        }
        return seat.stream().map(this::ConvertToDto).toList();
    }


    public SeatResponseDto getSeatById(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        return ConvertToDto(seat);
    }


    public SeatResponseDto updateSeat(Long id, SeatRequestDto seatRequestDto) {

        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        EventResponseDto event =
                eventClient.getEventId(seatRequestDto.getEventId());

        seat.setEventId(event.getId());
        seat.setSeatNumber(seatRequestDto.getSeatNumber());
        seat.setSeatType(seatRequestDto.getSeatType());
        seat.setPrice(seatRequestDto.getPrice());

        Seat updatedSeat = seatRepository.save(seat);

        return ConvertToDto(updatedSeat);
    }

    public void deleteById(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        seatRepository.delete(seat);

    }

    public List<SeatResponseDto> getAvailableSeats(Long eventId){
        List<Seat> seats = seatRepository.findByEventIdAndStatus(eventId, Status.AVAILABLE);
        if(seats.isEmpty()){
            throw new RuntimeException("Seats are empty");
        }
        return seats.stream().map(this::ConvertToDto).toList();

    }

    public SeatResponseDto bookSeat(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        if(seat.getStatus() == Status.BOOKED){
            throw new RuntimeException("Seat is already booked");
        }
        seat.setStatus(Status.BOOKED);
        Seat seat1 = seatRepository.save(seat);
        return ConvertToDto(seat1);
    }

    public SeatResponseDto releaseSeat(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        if(seat.getStatus() == Status.AVAILABLE){
            throw new RuntimeException("Seat is already booked");
        }
        seat.setStatus(Status.AVAILABLE);
        Seat seat1 = seatRepository.save(seat);
        return ConvertToDto(seat1);
    }
}
