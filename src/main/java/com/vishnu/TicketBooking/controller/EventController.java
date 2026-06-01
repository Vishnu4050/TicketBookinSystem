package com.vishnu.TicketBooking.controller;

import com.vishnu.TicketBooking.dto.EventResponseDTO;
import com.vishnu.TicketBooking.entity.Event;
import com.vishnu.TicketBooking.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventResponseDTO createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping
    public List<EventResponseDTO> getAllEvents() {
        return eventService.getAllEvents();
    }
    @GetMapping("/search")
    public List<EventResponseDTO> searchEvents(
            @RequestParam String title
    ) {

        return eventService.searchEvents(title);
    }
    @GetMapping("/paged")
    public Page<Event> getEventsWithPagination(

            @RequestParam int page,
            @RequestParam int size
    ) {

        return eventService.getEventsWithPagination(page, size);
    }
    @GetMapping("/sorted")
    public List<Event> getSortedEvents(

            @RequestParam String sortBy
    ) {

        return eventService.getSortedEvents(sortBy);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestBody Event event
    ) {
        return ResponseEntity.ok(
                eventService.updateEvent(id, event)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                eventService.deleteEvent(id)
        );
    }
}