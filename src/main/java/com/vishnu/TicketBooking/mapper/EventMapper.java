package com.vishnu.TicketBooking.mapper;

import com.vishnu.TicketBooking.dto.EventResponseDTO;
import com.vishnu.TicketBooking.entity.Event;

public class EventMapper {

    public static EventResponseDTO toDTO(Event event) {

        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getLocation(),
                event.getDate().toString(),
                event.getTotalSeats(),
                event.getAvailableSeats()
        );
    }
}