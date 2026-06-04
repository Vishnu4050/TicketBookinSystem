package com.vishnu.TicketBooking.repository;

import com.vishnu.TicketBooking.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findByDeletedFalse();
    List<Event> findAllByDeletedFalseOrderByUpdatedAtDesc();
}