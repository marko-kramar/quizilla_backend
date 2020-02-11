package com.quizilla.backend.controller.rest;

import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Question;
import com.quizilla.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "quizilla/api/questions/categories")
    public List<Category> findAllCategories() {
        return categoryService.findAll();
    }
}
