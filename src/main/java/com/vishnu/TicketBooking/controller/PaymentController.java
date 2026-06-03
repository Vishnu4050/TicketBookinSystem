package com.vishnu.TicketBooking.controller;

import com.vishnu.TicketBooking.entity.Payment;
import com.vishnu.TicketBooking.service.PaymentService;

import com.vishnu.TicketBooking.service.RazorpayService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
public class PaymentController {

    private final PaymentService paymentService;
    private final RazorpayService razorpayService;

    public PaymentController(
            PaymentService paymentService,
            RazorpayService razorpayService
    ) {
        this.paymentService = paymentService;
        this.razorpayService=razorpayService;
    }

    @PostMapping("/{bookingId}")
    public Payment makePayment(
            @PathVariable Long bookingId
    ) {

        return paymentService.makePayment(
                bookingId
        );
    }
    @PostMapping("/create-order")
    public String createOrder(
            @RequestParam Double amount
    ) throws Exception {

        return razorpayService.createOrder(amount);

    }
}