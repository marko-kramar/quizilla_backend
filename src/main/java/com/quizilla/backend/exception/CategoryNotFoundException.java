package com.quizilla.backend.exception;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(long categoryId) {
        super(String.format("Category with ID '%s' not found!", categoryId));
    }

}
