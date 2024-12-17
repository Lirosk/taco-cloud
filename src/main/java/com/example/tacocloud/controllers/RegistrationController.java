package com.example.tacocloud.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tacocloud.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String register() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm) {
        userRepository.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
