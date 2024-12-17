package com.example.tacocloud.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.tacocloud.users.CustomUserDetails;

import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public CustomUserDetails toUser(PasswordEncoder passwordEncoder) {
        return new CustomUserDetails(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
