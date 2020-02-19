package com.quizilla.backend.service.impl;

import com.quizilla.backend.model.Language;
import com.quizilla.backend.repository.LanguageRepository;
import com.quizilla.backend.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public Optional<Language> findLanguageById(Long languageId) {
        return languageRepository.findById(languageId);
    }
}
