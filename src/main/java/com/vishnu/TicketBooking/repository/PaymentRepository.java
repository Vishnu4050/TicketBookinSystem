package com.vishnu.TicketBooking.repository;

import com.vishnu.TicketBooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
    @Query("SELECT SUM(p.amount) FROM Payment p")
    Double getTotalRevenue();
    Optional<Payment> findByBookingId(Long bookingId);
}