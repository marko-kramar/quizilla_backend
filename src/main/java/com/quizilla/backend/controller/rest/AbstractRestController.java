package com.quizilla.backend.controller.rest;

import com.quizilla.backend.exception.QuizillaEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public abstract class AbstractRestController<T extends CrudRepository<U, Long>, U> {

    @Autowired
    private T repository;

    @GetMapping
    public List<U> findAll() {
        return (List<U>) repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public U findOne(@PathVariable("id") Long entityId) throws QuizillaEntityNotFoundException {
        return repository.findById(entityId)
                .orElseThrow(() -> new QuizillaEntityNotFoundException(entityId));
    }

    @PostMapping
    public U createOne(@Valid @RequestBody U entity) {
        return repository.save(entity);
    }

    @PutMapping(path = "/{id}")
    public U updateOne(@PathVariable("id") Long entityId,
                                   @Valid @RequestBody U requestEntity) throws QuizillaEntityNotFoundException {
        U entity = repository.findById(entityId)
                .orElseThrow(() -> new QuizillaEntityNotFoundException(entityId));

        setEntityFields(requestEntity, entity);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable("id") Long entityId) throws QuizillaEntityNotFoundException {
        U entity = repository.findById(entityId)
                .orElseThrow(() -> new QuizillaEntityNotFoundException(entityId));

        repository.delete(entity);

        return ResponseEntity.ok().build();
    }

    protected abstract void setEntityFields(U requestEntity, U fetchedEntity);
}
