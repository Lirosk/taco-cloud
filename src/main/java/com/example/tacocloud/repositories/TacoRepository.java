package com.example.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.tacocloud.models.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
