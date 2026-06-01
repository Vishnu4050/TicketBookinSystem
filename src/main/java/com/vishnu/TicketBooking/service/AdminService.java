package com.vishnu.TicketBooking.service;

import com.vishnu.TicketBooking.entity.Payment;
import com.vishnu.TicketBooking.entity.PaymentStatus;
import com.vishnu.TicketBooking.repository.PaymentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final PaymentRepository paymentRepository;

    public AdminService(
            PaymentRepository paymentRepository
    ) {
        this.paymentRepository = paymentRepository;
    }

    public Double getTotalRevenue() {

        Double revenue =
                paymentRepository.getTotalRevenue();

        return revenue != null ? revenue : 0.0;
    }
    public Double getRevenue() {

        List<Payment> payments =
                paymentRepository.findAll();

        return payments.stream()
                .filter(payment ->
                        payment.getStatus() == PaymentStatus.SUCCESS)
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}