package com.quizilla.backend.service.impl;

import com.quizilla.backend.model.Category;
import com.quizilla.backend.repository.CategoryRepository;
import com.quizilla.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }
}
