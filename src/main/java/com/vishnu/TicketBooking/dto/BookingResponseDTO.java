package com.vishnu.TicketBooking.dto;

import com.vishnu.TicketBooking.entity.BookingStatus;
import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingResponseDTO implements Serializable{

    private Long id;
    private String userEmail;
    private String eventTitle;
    private String location;
    private int seatsBooked;
    private BookingStatus status;
    private LocalDateTime bookedAt;
    private Double totalPrice;

    public BookingResponseDTO() {
    }

    public BookingResponseDTO(
            Long id,
            String userEmail,
            String eventTitle,
            String location,
            int seatsBooked,
            BookingStatus status,
            LocalDateTime bookedAt,
            Double totalPrice
    ) {
        this.id = id;
        this.userEmail = userEmail;
        this.eventTitle = eventTitle;
        this.location = location;
        this.seatsBooked = seatsBooked;
        this.status = status;
        this.bookedAt = bookedAt;
        this.totalPrice=totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getLocation() {
        return location;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }
}