package com.eventSeatBookingSystem.event_service.repository;

import com.eventSeatBookingSystem.event_service.dto.EventResponseDto;
import com.eventSeatBookingSystem.event_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByName(String name);

    List<Event> findByLocation(String location);

    List<Event> findByDate(LocalDate date);

    List<Event> findByPriceBetween(Double min, Double max);

    List<Event> findByDateAfter(LocalDate date);

}
