package com.quizilla.backend.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Question;
import com.quizilla.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(path = "/quizilla/api/questions")
    public List<Question> findAllQuestions() {
        return questionService.findAll();
    }

    @GetMapping(path = "/quizilla/api/questions/category/{category}")
    public List<Question> findQuestionsByCategory(@PathVariable String category) throws Exception {
        return questionService.findAllByCategory(new Category());
    }

    @GetMapping(path = "/quizilla/api/questions/random")
    public Question findRandomQuestion() {
        return questionService.findRandomQuestion();
    }


}
