package com.vishnu.TicketBooking.mapper;

import com.vishnu.TicketBooking.dto.BookingResponseDTO;
import com.vishnu.TicketBooking.entity.Booking;

public class BookingMapper {

    public static BookingResponseDTO toDTO(
            Booking booking
    ) {

        return new BookingResponseDTO(
                booking.getId(),
                booking.getUserEmail(),
                booking.getEvent().getTitle(),
                booking.getEvent().getLocation(),
                booking.getSeatsBooked(),
                booking.getStatus(),
                booking.getBookedAt()
        );
    }
}