package com.vishnu.TicketBooking.service;

import com.vishnu.TicketBooking.dto.EventResponseDTO;
import com.vishnu.TicketBooking.entity.Event;
import com.vishnu.TicketBooking.mapper.EventMapper;
import com.vishnu.TicketBooking.repository.BookingRepository;
import com.vishnu.TicketBooking.repository.EventRepository;
import com.vishnu.TicketBooking.repository.PaymentRepository;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.scheduling.annotation.Async;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public EventService(EventRepository eventRepository,BookingRepository bookingRepository,PaymentRepository paymentRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository=bookingRepository;
        this.paymentRepository=paymentRepository;
    }

//    @CacheEvict(value = "events", allEntries = true)
    public EventResponseDTO createEvent(Event event) {

        Event savedEvent = eventRepository.save(event);

        return EventMapper.toDTO(savedEvent);
    }

//    @Cacheable("events")
    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findByDeletedFalse()
                .stream()
                .map(EventMapper::toDTO)
                .toList();
    }

    public List<EventResponseDTO> searchEvents(String title) {

        return eventRepository
                .findByTitleContainingIgnoreCase(title)
                .stream()
                .map(EventMapper::toDTO)
                .toList();
    }

    public
    Page<Event> getEventsWithPagination(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return eventRepository.findAll(pageable);
    }

    public List<Event> getSortedEvents(String sortBy) {

        return eventRepository.findAll(
                Sort.by(sortBy)
        );
    }
//    @CacheEvict(value = "events", allEntries = true)
    public Event updateEvent(Long id, Event updatedEvent) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        event.setTitle(updatedEvent.getTitle());
        event.setLocation(updatedEvent.getLocation());
        event.setDate(updatedEvent.getDate());
        event.setAvailableSeats(updatedEvent.getAvailableSeats());
        event.setTicketPrice(updatedEvent.getTicketPrice());
        event.setImageUrl(updatedEvent.getImageUrl());

        return eventRepository.save(event);
    }
//    @CacheEvict(value = "events", allEntries = true)
//@Transactional
//public String deleteEvent(Long id) {
//
//    Event event = eventRepository.findById(id)
//            .orElseThrow(() ->
//                    new RuntimeException("Event not found"));
//
//    // First delete payments
//    paymentRepository.deleteByEventId(id);
//
//    // Then delete bookings
//    bookingRepository.deleteByEventId(id);
//
//    // Finally delete event
//    eventRepository.delete(event);
//
//    return "Event deleted successfully";
//}
@Transactional
public String deleteEvent(Long id) {

    Event event = eventRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Event not found"));

    event.setDeleted(true);

    eventRepository.save(event);

    return "Event deleted successfully";
}
    public EventResponseDTO getEventById(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        return EventMapper.toDTO(event);

    }
}