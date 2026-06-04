package com.vishnu.TicketBooking.service;

import com.vishnu.TicketBooking.dto.BookingRequestDTO;
import com.vishnu.TicketBooking.dto.BookingResponseDTO;
import com.vishnu.TicketBooking.entity.*;
import com.vishnu.TicketBooking.mapper.BookingMapper;
import com.vishnu.TicketBooking.repository.BookingRepository;
import com.vishnu.TicketBooking.repository.EventRepository;
import com.vishnu.TicketBooking.repository.PaymentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.vishnu.TicketBooking.kafka.BookingProducer;
import com.vishnu.TicketBooking.kafka.BookingEvent;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final PaymentRepository paymentRepository;
    private final BookingProducer bookingProducer;

    public BookingService(BookingRepository bookingRepository,
                          EventRepository eventRepository, PaymentRepository paymentRepository, BookingProducer bookingProducer) {

        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.paymentRepository=paymentRepository;
        this.bookingProducer = bookingProducer;
    }
    @CacheEvict(value = "bookings", allEntries = true)
    public BookingResponseDTO bookTicket(Long eventId, BookingRequestDTO dto,String userEmail){

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        if (event.getAvailableSeats() < dto.getSeats()) {
            throw new RuntimeException("Not enough seats available");
        }

        event.setAvailableSeats(
                event.getAvailableSeats() - dto.getSeats()
        );

        eventRepository.save(event);

        Booking booking = new Booking();
        booking.setBookedAt(LocalDateTime.now());

        booking.setUserEmail(userEmail);

        booking.setSeatsBooked(dto.getSeats());

        booking.setEvent(event);

        booking.setTotalPrice(
                dto.getSeats() *
                        event.getTicketPrice()
        );

        booking.setStatus(BookingStatus.PENDING);

        Booking savedBooking = bookingRepository.save(booking);
//        bookingProducer.sendBookingEvent(
//                new BookingEvent(
//                        savedBooking.getUserEmail(),
//                        savedBooking.getEvent().getTitle()
//                )
//        );

        return BookingMapper.toDTO(savedBooking);
    }
    @Cacheable("bookings")
    public List<BookingResponseDTO> getMyBookings(String email) {

        List<Booking> bookings =
                bookingRepository.findByUserEmail(email);

        return bookings.stream()
                .map(BookingMapper::toDTO)
                .toList();
    }
    @CacheEvict(value = "bookings", allEntries = true)
    public String cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking already cancelled");
        }

        Event event = booking.getEvent();

        event.setAvailableSeats(
                event.getAvailableSeats() + booking.getSeatsBooked()
        );
        booking.setStatus(BookingStatus.CANCELLED);
        Payment payment = paymentRepository
                .findByBookingId(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.REFUNDED);

        paymentRepository.save(payment);

        eventRepository.save(event);

        bookingRepository.save(booking);

        return "Booking cancelled successfully";
    }
}