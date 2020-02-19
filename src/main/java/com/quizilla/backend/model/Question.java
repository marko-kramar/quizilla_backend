package com.quizilla.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

@Entity
@Data
@Table(name = "questions")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private Category category;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Integer, String> answers = new TreeMap<>();

    @ManyToOne
    @NotNull
    private Language language;

    @Column(name = "correct_answer_id")
    private int correctAnswerId;

    @NotNull
    @NotEmpty
    private String question;
}
