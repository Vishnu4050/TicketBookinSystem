package com.vishnu.TicketBooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Welcome Authenticated User!";
    }
    @GetMapping("/admin")
    public String admin() {
        return "Welcome Admin!";
    }
    @GetMapping("/user")
    public String user() {
        return "Welcome User!";
    }
}