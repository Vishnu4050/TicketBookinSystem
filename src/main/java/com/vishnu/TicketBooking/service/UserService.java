package com.vishnu.TicketBooking.service;

import com.vishnu.TicketBooking.dto.LoginRequestDTO;
import com.vishnu.TicketBooking.dto.LoginResponseDTO;
import com.vishnu.TicketBooking.dto.UserRequestDTO;
import com.vishnu.TicketBooking.dto.UserResponseDTO;
import com.vishnu.TicketBooking.entity.User;
import com.vishnu.TicketBooking.exception.EmailAlreadyExistsException;
import com.vishnu.TicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vishnu.TicketBooking.security.JwtService;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public UserResponseDTO saveUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        user.setRole(dto.getRole());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public User updateUser(Long id, User updatedUser) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());

            return userRepository.save(existingUser);
        }

        return null;
    }

    public String deleteUser(Long id) {

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
    public LoginResponseDTO loginUser(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (user == null) {
            return new LoginResponseDTO("User not found");
        }

        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword())) {
            return new LoginResponseDTO("Invalid password");
        }

        String token = jwtService.generateToken(
                user.getEmail()
        );

        return new LoginResponseDTO(token);
    }
}