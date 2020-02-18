package com.quizilla.backend.service.impl;

import com.quizilla.backend.model.Category;
import com.quizilla.backend.repository.CategoryRepository;
import com.quizilla.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findCategoryByCode(String categoryCode) {
        final List<Category> categories = (List<Category>) categoryRepository.findAll();
        if (categories.isEmpty()) {
            return Optional.empty();
        }

        return categories.stream()
                .filter(category -> category.getCode() != null && category.getCode().equalsIgnoreCase(categoryCode))
                .findFirst();
    }

}
