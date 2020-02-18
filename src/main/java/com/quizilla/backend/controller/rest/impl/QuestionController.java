package com.quizilla.backend.controller.rest.impl;

import com.quizilla.backend.controller.rest.AbstractRestController;
import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Language;
import com.quizilla.backend.model.Question;
import com.quizilla.backend.repository.QuestionRepository;
import com.quizilla.backend.service.CategoryService;
import com.quizilla.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/quizilla/api/questions")
public class QuestionController extends AbstractRestController<QuestionRepository, Question> {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/category/{categoryCode}")
    public List<Question> findQuestionsByCategory(@PathVariable String categoryCode) {
        final Optional<Category> optCategory = categoryService.findCategoryByCode(categoryCode);
        if (!optCategory.isPresent()) {
            return new ArrayList<>();
        }

        return questionService.findAllByCategory(optCategory.get());
    }

    @GetMapping(path = "/random")
    public Question findRandomQuestion() {
        //return questionService.findRandomQuestion();
        Question question = new Question();
        Map<Integer, String> answers = new TreeMap<>();
        answers.put(1, "Answer one");
        answers.put(2, "Answer two");
        question.setCategory(categoryService.findCategoryByCode("geography").orElse(null));

        Language language = new Language();
        language.setId(5L);
        language.setCode("en");
        language.setName("English");
        question.setLanguage(language);
        question.setCorrectAnswerId(1);
        question.setQuestion("What is the name of something?");

        /*
        {
            "id": null,
                "category": {
            "id": 3,
                    "code": "geography",
                    "name": "Geography",
                    "description": "Category about geography..."
        },
            "answers": {},
            "language": {
            "id": 5,
                    "code": "en",
                    "name": "English"
        },
            "correctAnswerId": 1,
                "question": "What is the name of something?"
        }

        */

        return question;
    }

    @Override
    protected void setEntityFields(Question requestEntity, Question fetchedEntity) {

    }
}
