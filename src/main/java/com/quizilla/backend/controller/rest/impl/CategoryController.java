package com.quizilla.backend.controller.rest.impl;

import com.quizilla.backend.controller.rest.AbstractRestController;
import com.quizilla.backend.repository.CategoryRepository;
import com.quizilla.backend.model.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizilla/api/categories")
public class CategoryController extends AbstractRestController<CategoryRepository, Category> {

    @Override
    protected void setEntityFields(Category requestEntity, Category fetchedEntity) {
        fetchedEntity.setCode(requestEntity.getCode());
        fetchedEntity.setName(requestEntity.getName());
        fetchedEntity.setDescription(requestEntity.getDescription());
    }

}
