package com.vishnu.TicketBooking.dto;

public class BookingRequestDTO {

    private Long eventId;

    private int seats;

    private double totalPrice;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long eventId, int seats,double totalPrice) {
        this.eventId = eventId;
        this.seats = seats;
        this.totalPrice=totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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