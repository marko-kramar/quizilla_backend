package com.quizilla.backend.service;

import com.quizilla.backend.model.Language;

import java.util.Optional;

public interface LanguageService {

    Optional<Language> findLanguageById(Long languageId);

}
