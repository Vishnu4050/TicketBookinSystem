package com.vishnu.TicketBooking.service;

import com.vishnu.TicketBooking.entity.*;
import com.vishnu.TicketBooking.repository.BookingRepository;
import com.vishnu.TicketBooking.repository.PaymentRepository;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final EmailService emailService;

    public PaymentService(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository,
            EmailService emailService
    ) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }
    public Payment makePayment(Long bookingId) {
    System.out.println("PAYMENT METHOD STARTED");

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        if (booking.getStatus() ==
                BookingStatus.CONFIRMED) {

            throw new RuntimeException(
                    "Payment already completed");
        }

        Payment payment = new Payment();

        payment.setBooking(booking);

        payment.setAmount(
                booking.getSeatsBooked() * booking.getEvent().getTicketPrice()
        );

        payment.setTransactionId(
                UUID.randomUUID().toString()
        );

        payment.setStatus(PaymentStatus.SUCCESS);

        booking.setStatus(BookingStatus.CONFIRMED);
        System.out.println("ABOUT TO SEND EMAIL");
        String body =

                "Hello,\n\n"

                        + "Your ticket booking has been CONFIRMED 🎉\n\n"

                        + "Event: "
                        + booking.getEvent().getTitle()

                        + "\nLocation: "
                        + booking.getEvent().getLocation()

                        + "\nSeats Booked: "
                        + booking.getSeatsBooked()

                        + "\nTotal Amount: ₹"
                        + payment.getAmount()

                        + "\nBooked Time: "
                        + booking.getBookedAt()

                        + "\nBooking Status: "
                        + booking.getStatus()

                        + "\n\nThank you for booking with us!";

        System.out.println("ABOUT TO SEND EMAIL");
        System.out.println("EMAIL = " + booking.getUserEmail());

        emailService.sendEmail(
                booking.getUserEmail(),
                "Ticket Booking Confirmed 🎟️",
                body
        );
        System.out.println("EMAIL METHOD FINISHED");

        bookingRepository.save(booking);

        return paymentRepository.save(payment);
    }
}