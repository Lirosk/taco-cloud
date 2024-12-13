package com.example.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.tacocloud.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
