package com.eventSeatBookingSystem.seat_service.repository;

import com.eventSeatBookingSystem.seat_service.entity.Seat;
import com.eventSeatBookingSystem.seat_service.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long>{

    List<Seat> findByEventIdAndStatus(Long eventId, Status status);

}
