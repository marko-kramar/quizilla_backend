package com.quizilla.backend.service.impl;

import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Question;
import com.quizilla.backend.repository.QuestionRepository;
import com.quizilla.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public List<Question> findAllByCategory(Category category) {
        return findAll().parallelStream()
                .filter(question -> question.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public Question findRandomQuestion() {
        return findAll().get(new Random().nextInt((int)questionRepository.count()));
    }

}
