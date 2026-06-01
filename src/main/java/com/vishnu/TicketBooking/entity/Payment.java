package com.vishnu.TicketBooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Payment() {
    }

    public Payment(Long id, double amount,
                   String transactionId,
                   PaymentStatus status,
                   Booking booking) {

        this.id = id;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}