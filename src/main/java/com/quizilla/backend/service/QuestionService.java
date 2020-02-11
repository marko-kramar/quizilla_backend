package com.quizilla.backend.service;

import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    List<Question> findAllByCategory(Category category);

    Question findRandomQuestion();

}
