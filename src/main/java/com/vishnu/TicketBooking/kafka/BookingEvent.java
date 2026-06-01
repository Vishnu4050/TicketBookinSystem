package com.vishnu.TicketBooking.kafka;

public class BookingEvent {

    private String email;

    private String eventTitle;

    public BookingEvent() {
    }

    public BookingEvent(
            String email,
            String eventTitle
    ) {
        this.email = email;
        this.eventTitle = eventTitle;
    }

    public String getEmail() {
        return email;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}