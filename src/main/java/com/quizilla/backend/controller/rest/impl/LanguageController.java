package com.quizilla.backend.controller.rest.impl;

import com.quizilla.backend.controller.rest.AbstractRestController;
import com.quizilla.backend.model.Language;
import com.quizilla.backend.repository.LanguageRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quizilla/api/languages")
public class LanguageController extends AbstractRestController<LanguageRepository, Language> {

    @Override
    protected void setEntityFields(Language requestEntity, Language fetchedEntity) {
        fetchedEntity.setCode(requestEntity.getCode());
        fetchedEntity.setName(requestEntity.getName());
    }

}
