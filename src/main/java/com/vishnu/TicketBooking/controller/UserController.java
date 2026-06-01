package com.vishnu.TicketBooking.controller;

import com.vishnu.TicketBooking.dto.LoginRequestDTO;
import com.vishnu.TicketBooking.dto.LoginResponseDTO;
import com.vishnu.TicketBooking.dto.UserRequestDTO;
import com.vishnu.TicketBooking.dto.UserResponseDTO;
import com.vishnu.TicketBooking.entity.User;
import com.vishnu.TicketBooking.security.JwtService;
import com.vishnu.TicketBooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final JwtService jwtService;
    public UserController(UserService userService,
                          JwtService jwtService) {

        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO dto) {

        return userService.saveUser(dto);
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PostMapping("/login")
    public LoginResponseDTO loginUser(
            @RequestBody LoginRequestDTO dto) {

        return userService.loginUser(dto);
    }
    
    public String getToken() {

        return jwtService.generateToken(
                "vishnu@gmail.com"
        );
    }

}