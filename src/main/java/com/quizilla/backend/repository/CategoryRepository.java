package com.quizilla.backend.repository;

import com.quizilla.backend.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
