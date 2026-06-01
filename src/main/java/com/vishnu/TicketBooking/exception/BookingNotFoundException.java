package com.vishnu.TicketBooking.exception;

public class BookingNotFoundException
        extends RuntimeException {

    public BookingNotFoundException(String message) {
        super(message);
    }
}