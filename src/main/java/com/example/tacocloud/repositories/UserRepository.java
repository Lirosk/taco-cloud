package com.example.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.tacocloud.users.CustomUserDetails;

public interface UserRepository extends CrudRepository<CustomUserDetails, Long> {
    CustomUserDetails findByUsername(String username);
}
