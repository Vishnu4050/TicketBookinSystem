package com.vishnu.TicketBooking.repository;

import com.vishnu.TicketBooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    List<Booking> findByUserEmail(String userEmail);

    @Modifying
    @Query("DELETE FROM Booking b WHERE b.event.id = :eventId")
    void deleteByEventId(Long eventId);
}