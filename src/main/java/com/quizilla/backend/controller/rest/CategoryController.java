package com.quizilla.backend.controller.rest;

import com.quizilla.backend.exception.CategoryNotFoundException;
import com.quizilla.backend.model.Category;
import com.quizilla.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/quizilla/api/categories")
    public List<Category> findAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping(path = "/quizilla/api/categories/{id}")
    public Category findCategory(@PathVariable("id") Long categoryId) throws CategoryNotFoundException {
        return categoryService.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @PostMapping(path = "/quizilla/api/categories")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping(path = "/quizilla/api/categories/{id}")
    public Category updateCategory(@PathVariable("id") Long categoryId,
                                   @Valid @RequestBody Category categoryDetails) throws CategoryNotFoundException {
        Category category = categoryService.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        category.setName(categoryDetails.getName());
        return categoryService.save(category);
    }

    @DeleteMapping("/quizilla/api/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long categoryId) throws CategoryNotFoundException {
        Category category = categoryService.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        categoryService.delete(category);

        return ResponseEntity.ok().build();
    }
}
