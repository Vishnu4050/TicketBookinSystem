package com.vishnu.TicketBooking.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    private final KafkaTemplate<String, BookingEvent>
            kafkaTemplate;

    public BookingProducer(
            KafkaTemplate<String, BookingEvent>
                    kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookingEvent(
            BookingEvent event
    ) {

        kafkaTemplate.send(
                "booking-topic",
                event
        );
    }
}