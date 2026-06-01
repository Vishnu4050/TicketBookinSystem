package com.vishnu.TicketBooking.controller;

import com.vishnu.TicketBooking.dto.BookingRequestDTO;
import com.vishnu.TicketBooking.dto.BookingResponseDTO;
import com.vishnu.TicketBooking.service.BookingService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{eventId}")
    public BookingResponseDTO bookTicket(

            @PathVariable Long eventId,

            @RequestBody BookingRequestDTO dto,

            Authentication authentication
    ) {

        String userEmail = authentication.getName();

        return bookingService.bookTicket(
                eventId,
                dto,
                userEmail
        );
    }

    @GetMapping("/my")
    public List<BookingResponseDTO> getMyBookings(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return bookingService.getMyBookings(email);
    }

    @DeleteMapping("/{id}")
    public String cancelBooking(
            @PathVariable Long id
    ) {

        return bookingService.cancelBooking(id);
    }
}