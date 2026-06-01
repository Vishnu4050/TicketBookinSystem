package com.vishnu.TicketBooking.kafka;

import com.vishnu.TicketBooking.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class BookingConsumer {

    private final EmailService emailService;

    public BookingConsumer(
            EmailService emailService
    ) {
        this.emailService = emailService;
    }

//    @KafkaListener(
//            topics = "booking-topic",
//            groupId = "ticket-booking-group"
//    )
    public void consume(
            BookingEvent event
    ) {

        emailService.sendEmail(
                event.getEmail(),
                "Ticket Confirmed",
                "Your booking for "
                        + event.getEventTitle()
                        + " is confirmed."
        );

        System.out.println(
                "Kafka consumed booking event"
        );
    }
}