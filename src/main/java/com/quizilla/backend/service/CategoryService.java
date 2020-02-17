package com.quizilla.backend.service;

import com.quizilla.backend.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findById(Long categoryId);

    List<Category> findAll();

    Category save(Category category);

    void delete(Category category);

}
