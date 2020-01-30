package com.quizilla.backend.model;

import java.util.List;
import java.util.UUID;

public class Question {
    private UUID id;
    private String question;
    private String category;
    private List<Answer> answers;
    private int correctAnswerId;

    public Question(final UUID id, final String question, final String category, final List<Answer> answers, final int correctAnswerId) {
        this.id = id;
        this.question = question;
        this.category = category;
        this.answers = answers;
        this.correctAnswerId = correctAnswerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }
}
