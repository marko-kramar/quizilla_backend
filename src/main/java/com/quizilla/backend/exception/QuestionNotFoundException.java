package com.quizilla.backend.exception;

public class QuestionNotFoundException extends Exception {

    public QuestionNotFoundException(long questionId) {
        super(String.format("Question with ID '%s' not found!", questionId));
    }

}
