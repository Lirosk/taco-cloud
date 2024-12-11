package com.example.tacocloud.misc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.tacocloud.models.Ingredient;
import com.example.tacocloud.repositories.IngredientRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IngredientByIdController implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
