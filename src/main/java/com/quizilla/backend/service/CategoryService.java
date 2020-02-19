package com.quizilla.backend.service;

import com.quizilla.backend.model.Category;

import java.util.Optional;

public interface CategoryService {

    Optional<Category> findCategoryByCode(String categoryCode);

    Optional<Category> findCategoryBdId(Long categoryId);

}
