package com.foursales.desafio.domain.validations;

import com.foursales.desafio.domain.enums.Category;
import jakarta.validation.ValidationException;

import java.util.Arrays;

public class CategoryValidation {
    public static boolean isValidCategory(String categoryName) {
        return Arrays.stream(Category.values())
                .anyMatch(category -> category.name().equals(categoryName));
    }
}
