package com.quizilla.backend.repository;

import com.quizilla.backend.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
