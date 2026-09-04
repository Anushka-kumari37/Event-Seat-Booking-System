package com.eventSystemBookingSystem.booking_service.service;

import com.eventSystemBookingSystem.booking_service.client.EventClient;
import com.eventSystemBookingSystem.booking_service.client.SeatClient;
import com.eventSystemBookingSystem.booking_service.client.UserClient;
import com.eventSystemBookingSystem.booking_service.dto.*;
import com.eventSystemBookingSystem.booking_service.entity.Booking;
import com.eventSystemBookingSystem.booking_service.entity.BookingStatus;
import com.eventSystemBookingSystem.booking_service.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventClient eventClient;
    private final UserClient userClient;
    private final SeatClient seatClient;

    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {

        UserResponseDto user = userClient.getUser(bookingRequestDto.getUserId());
        EventResponseDto event = eventClient.getEvent(bookingRequestDto.getEventId());
        SeatResponseDto seat = seatClient.getSeat(bookingRequestDto.getSeatId());

        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setSeatId(seat.getId());
        booking.setEventId(event.getId());

        booking.setTotalAmount(seat.getPrice());
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setBookingDate(LocalDateTime.now());

        Booking booking1 = bookingRepository.save(booking);
        seatClient.bookSeat(seat.getId());
        return ConvertToDto(booking1);

    }

    private BookingResponseDto ConvertToDto(Booking booking1) {
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(booking1.getId());
        bookingResponseDto.setEventId(booking1.getEventId());
        bookingResponseDto.setSeatId(booking1.getSeatId());
        bookingResponseDto.setUserId(booking1.getUserId());
        bookingResponseDto.setTotalAmount(booking1.getTotalAmount());
        bookingResponseDto.setBookingStatus(booking1.getBookingStatus());
        bookingResponseDto.setBookingDate(booking1.getBookingDate());

        return bookingResponseDto;

    }

    public List<BookingResponseDto> getAllBooking() {
        List<Booking> book = bookingRepository.findAll();
        if(book.isEmpty()){
            throw new RuntimeException("booking not found");
        }
        return book.stream().map(this::ConvertToDto).toList();
    }


    public BookingResponseDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("booking not found"));
        return ConvertToDto(booking);
    }

    public List<BookingResponseDto> getBookingByUserId(Long userId) {
        List<Booking> booking = bookingRepository.findByUserId(userId);
        if(booking.isEmpty()){
            throw new RuntimeException("booking not found with this user "+userId);
        }
        return booking.stream().map(this::ConvertToDto).toList();
    }

    public List<BookingResponseDto> getBookingsByEventId(Long eventId) {
        List<Booking> booking = bookingRepository.findByEventId(eventId);
        if(booking.isEmpty()){
            throw new RuntimeException("booking not found with this user "+eventId);
        }
        return booking.stream().map(this::ConvertToDto).toList();
    }

    public void deleteBooking(Long id) {
        Booking booking =  bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("booking not found"));
        bookingRepository.delete(booking);

    }

    public BookingResponseDto cancelBooking(Long id, BookingRequestDto bookingRequestDto) {

        Booking booking =  bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("booking not found"));
        if(booking.getBookingStatus().equals(BookingStatus.CANCELLED)){
            throw new RuntimeException("booking is already cancelled");
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        Booking booking1 =bookingRepository.save(booking);
        return ConvertToDto(booking1);
    }
}
