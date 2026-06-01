package com.vishnu.TicketBooking.dto;

public class BookingRequestDTO {

    private Long eventId;

    private int seats;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long eventId, int seats) {
        this.eventId = eventId;
        this.seats = seats;
    }

    public Long getEventId() {
        return eventId;
    }

    public int getSeats() {
        return seats;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}