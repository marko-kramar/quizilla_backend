package com.quizilla.backend.repository;

import com.quizilla.backend.model.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
