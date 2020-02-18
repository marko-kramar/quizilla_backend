package com.quizilla.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Category category;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Integer, String> answers = new TreeMap<>();

    @ManyToOne
    private Language language;

    private int correctAnswerId;
    private String question;
}
