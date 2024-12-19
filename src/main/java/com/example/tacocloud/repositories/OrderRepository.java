package com.example.tacocloud.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import com.example.tacocloud.models.Order;
import com.example.tacocloud.users.CustomUserDetails;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserOrderByPlacedAtDesc(CustomUserDetails user, PageRequest pageRequest);
}
