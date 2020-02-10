package com.quizilla.backend.model;

import java.util.List;

public class Question {
    private Long id;
    private String question;
    private Category category;
    private List<Answer> answers;
    private Long correctAnswerId;
    private Language language;

    public Question(Long id, String question, Category category, List<Answer> answers, Long correctAnswerId, Language language) {
        this.id = id;
        this.question = question;
        this.category = category;
        this.answers = answers;
        this.correctAnswerId = correctAnswerId;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Long getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(Long correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
