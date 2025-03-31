package com.foursales.desafio.domain.exceptions;

import com.foursales.desafio.domain.enums.Category;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String category) {
        super("Invalid category: " + category + ". Valid categories are: " + Arrays.stream(Category.values()).map(Enum::name).collect(Collectors.joining(", ")));
    }
}
