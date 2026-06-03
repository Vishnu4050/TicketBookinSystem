package com.vishnu.TicketBooking.mapper;

import com.vishnu.TicketBooking.dto.EventResponseDTO;
import com.vishnu.TicketBooking.entity.Event;

public class EventMapper {

    public static EventResponseDTO toDTO(Event event) {

        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getLocation(),
                event.getDate() != null ? event.getDate().toString() : null,
                event.getTotalSeats(),
                event.getAvailableSeats(),
                event.getTicketPrice(),
                event.getImageUrl()
        );
    }
}