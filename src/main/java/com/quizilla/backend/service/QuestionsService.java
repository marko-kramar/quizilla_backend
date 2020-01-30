package com.quizilla.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizilla.backend.dao.QuestionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionsService {

    @Autowired
    private QuestionsDao questionsDao;

    @GetMapping(path = "quizilla/api/questions")
    public String findAllQuestions() throws Exception {
        return new ObjectMapper().writeValueAsString(questionsDao.findAllQuestions());
    }

    @GetMapping(path = "quizilla/api/questions/category/{category}")
    public String find(@PathVariable String category) throws Exception {
        return new ObjectMapper().writeValueAsString(questionsDao.findQuestionsByCategory(category));
    }

    @GetMapping(path = "quizilla/api/questions/categories")
    public String findCategories() throws Exception {
        return new ObjectMapper().writeValueAsString(questionsDao.findCategories());
    }

    @GetMapping(path = "quizilla/api/questions/random")
    public String findRandomQuestion() throws Exception {
        return new ObjectMapper().writeValueAsString(questionsDao.findRandomQuestion());
    }

}
