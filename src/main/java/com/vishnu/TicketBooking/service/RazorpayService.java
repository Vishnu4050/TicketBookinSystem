package com.vishnu.TicketBooking.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public String createOrder(
            Double amount
    ) throws Exception {

        RazorpayClient razorpayClient =
                new RazorpayClient(
                        razorpayKeyId,
                        razorpayKeySecret
                );

        JSONObject options =
                new JSONObject();

        options.put(
                "amount",
                amount * 100
        );

        options.put(
                "currency",
                "INR"
        );

        options.put(
                "receipt",
                "txn_123456"
        );

        Order order =
                razorpayClient.orders.create(options);

        return order.toString();
    }
}