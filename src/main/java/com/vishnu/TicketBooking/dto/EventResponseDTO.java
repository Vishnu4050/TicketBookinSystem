package com.vishnu.TicketBooking.dto;

import java.time.LocalDate;

public class EventResponseDTO {

    private Long id;

    private String title;

    private String location;

    private String date;

    private int totalSeats;

    private int availableSeats;

    public EventResponseDTO(
            Long id,
            String title,
            String location,
            String date,
            int totalSeats,
            int availableSeats
    ) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.date = date;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}