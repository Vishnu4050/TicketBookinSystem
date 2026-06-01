package com.vishnu.TicketBooking.controller;

import com.vishnu.TicketBooking.entity.Payment;
import com.vishnu.TicketBooking.entity.PaymentStatus;
import com.vishnu.TicketBooking.service.AdminService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    public AdminController(
            AdminService adminService
    ) {
        this.adminService = adminService;
    }

    @GetMapping("/revenue")
    public Double getRevenue() {

        return adminService.getRevenue();
    }
}