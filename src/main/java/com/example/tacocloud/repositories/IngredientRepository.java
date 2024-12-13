package com.example.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.tacocloud.models.Ingredient;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
