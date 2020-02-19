package com.quizilla.backend.controller.rest.impl;

import com.quizilla.backend.dto.QuestionDto;
import com.quizilla.backend.exception.QuizillaEntityNotFoundException;
import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Question;
import com.quizilla.backend.repository.QuestionRepository;
import com.quizilla.backend.service.CategoryService;
import com.quizilla.backend.service.LanguageService;
import com.quizilla.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quizilla/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<Question> findAll() {
        return (List<Question>) questionRepository.findAll();
    }

    @PostMapping
    public Question createOne(@Valid @RequestBody QuestionDto questionDto) throws QuizillaEntityNotFoundException {
        Question questionToSave = new Question();
        mapQuestionDtoToQuestionEntity(questionDto, questionToSave);
        return questionRepository.save(questionToSave);
    }

    @PutMapping(path = "/{id}")
    public Question updateOne(@PathVariable("id") Long entityId,
                       @Valid @RequestBody QuestionDto questionDto) throws QuizillaEntityNotFoundException {
        Question questionFromDatabase = questionRepository.findById(entityId)
                .orElseThrow(() -> new QuizillaEntityNotFoundException(entityId));

        mapQuestionDtoToQuestionEntity(questionDto, questionFromDatabase);
        return questionRepository.save(questionFromDatabase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable("id") Long entityId) throws QuizillaEntityNotFoundException {
        Question questionToDelete = questionRepository.findById(entityId)
                .orElseThrow(() -> new QuizillaEntityNotFoundException(entityId));
        questionRepository.delete(questionToDelete);
        return ResponseEntity.ok().build();
    }

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
        return questionService.findRandomQuestion();
    }

    private void mapQuestionDtoToQuestionEntity(final QuestionDto questionDto, Question question) throws QuizillaEntityNotFoundException{
        question.setQuestion(questionDto.getQuestion());
        question.setAnswers(questionDto.getAnswers());
        question.setCategory(categoryService.findCategoryBdId(questionDto.getCategoryId())
                .orElseThrow(() -> new QuizillaEntityNotFoundException(questionDto.getCategoryId())));
        question.setCorrectAnswerId(questionDto.getCorrectAnswerId());
        question.setLanguage(languageService.findLanguageById(questionDto.getLanguageId())
                .orElseThrow(() -> new QuizillaEntityNotFoundException(questionDto.getLanguageId())));
    }


}
