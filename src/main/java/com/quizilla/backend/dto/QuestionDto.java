package com.quizilla.backend.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

@Data
public class QuestionDto implements Serializable {
    private String question;
    private Long categoryId;
    private Long languageId;
    private Map<Integer, String> answers = new TreeMap<>();
    private int correctAnswerId;
}
