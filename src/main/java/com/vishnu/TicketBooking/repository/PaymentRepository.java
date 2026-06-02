package com.vishnu.TicketBooking.repository;

import com.vishnu.TicketBooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
    @Query("SELECT SUM(p.amount) FROM Payment p")
    Double getTotalRevenue();
    Optional<Payment> findByBookingId(Long bookingId);
    @Modifying
    @Transactional
    @Query("""
DELETE FROM Payment p
WHERE p.booking.event.id = :eventId
""")
    void deleteByEventId(Long eventId);
}