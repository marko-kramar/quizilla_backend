package com.quizilla.backend.exception;

import net.bytebuddy.implementation.bytecode.Throw;

public class QuizillaEntityNotFoundException extends Throwable {

    public QuizillaEntityNotFoundException(Object entityId) {
        super(String.format("Entity with ID '%s' not found!", entityId));
    }

}
