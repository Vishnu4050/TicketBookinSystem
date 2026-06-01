package com.vishnu.TicketBooking.controller;

import com.vishnu.TicketBooking.entity.Payment;
import com.vishnu.TicketBooking.service.PaymentService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{bookingId}")
    public Payment makePayment(
            @PathVariable Long bookingId
    ) {

        return paymentService.makePayment(
                bookingId
        );
    }
}