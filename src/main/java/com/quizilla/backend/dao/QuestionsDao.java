package com.quizilla.backend.dao;

import com.quizilla.backend.model.Answer;
import com.quizilla.backend.model.Question;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class QuestionsDao {
    private static final List<Question> questions = new ArrayList<>();

    static {
        questions.add(new Question(UUID.randomUUID(), "What is the capital of Croatia?", "Geography", Arrays.asList(
                new Answer(1, "Berlin"),
                new Answer(2, "Zagreb"),
                new Answer(3, "Budapest"),
                new Answer(4, "Sarajevo")
        ), 2));

        questions.add(new Question(UUID.randomUUID(), "How many natural satellites does the Earth have?", "Astronomy", Arrays.asList(
                new Answer(1, "1"),
                new Answer(2, "2"),
                new Answer(3, "3"),
                new Answer(4, "4")
        ), 1));

        questions.add(new Question(UUID.randomUUID(), "How many continents does the Earth have?", "Geography", Arrays.asList(
                new Answer(1, "5"),
                new Answer(2, "6"),
                new Answer(3, "7"),
                new Answer(4, "11")
        ), 3));

        questions.add(new Question(UUID.randomUUID(), "Where does U2 music band come from?", "Music", Arrays.asList(
                new Answer(1, "Germany"),
                new Answer(2, "Croatia"),
                new Answer(3, "Netherlands"),
                new Answer(4, "Ireland")
        ), 4));

        questions.add(new Question(UUID.randomUUID(), "Which US president was killed by rifle shot in Dallas, 1963?", "Famous people", Arrays.asList(
                new Answer(1, "Franklin D. Roosevelt"),
                new Answer(2, "George W. Bush"),
                new Answer(3, "John F. Kennedy"),
                new Answer(4, "Abraham Lincoln")
        ), 3));

        questions.add(new Question(UUID.randomUUID(), "What is Nintendo's most popular franchise?", "Gaming", Arrays.asList(
                new Answer(1, "Star Wars"),
                new Answer(2, "Super Mario"),
                new Answer(3, "Avengers"),
                new Answer(4, "Doctor Who")
        ), 2));

        questions.add(new Question(UUID.randomUUID(), "'To be, or not to be, that is the question.' This line is from which Shakespeare novel?", "Literature", Arrays.asList(
                new Answer(1, "Hamlet"),
                new Answer(2, "Othello"),
                new Answer(3, "King Lear"),
                new Answer(4, "Romeo and Juliet")
        ), 1));

        questions.add(new Question(UUID.randomUUID(), "What is the name of Microsoft's gaming console series?", "Gaming", Arrays.asList(
                new Answer(1, "PlayStation"),
                new Answer(2, "GameCube"),
                new Answer(3, "Sega MegaDrive"),
                new Answer(4, "Xbox")
        ), 4));

        questions.add(new Question(UUID.randomUUID(), "What is the name of the star in our own Solar system?", "Gaming", Arrays.asList(
                new Answer(1, "Proxima Centauri"),
                new Answer(2, "Jupiter"),
                new Answer(3, "Sun"),
                new Answer(4, "Earth")
        ), 3));

        questions.add(new Question(UUID.randomUUID(), "Where does the famous philosopher Socrates come from?", "Famous people", Arrays.asList(
                new Answer(1, "Ancient Greece"),
                new Answer(2, "China"),
                new Answer(3, "Rome"),
                new Answer(4, "Russia")
        ), 1));

        questions.add(new Question(UUID.randomUUID(), "'Dancing Queen' is pop music hit from which band?", "Music", Arrays.asList(
                new Answer(1, "Beatles"),
                new Answer(2, "Guns'n'Roses"),
                new Answer(3, "ABBA"),
                new Answer(4, "Psihomodo Pop")
        ), 3));
    }


    public List<Question> findAllQuestions() {
        return questions;
    }

    public List<Question> findQuestionsByCategory(final String category) {
        return questions.parallelStream().filter(question -> question.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public Question findRandomQuestion() {
        return questions.get(new Random().nextInt(questions.size()));
    }

    public List<String> findCategories() {
        return questions.parallelStream().map(Question::getCategory).distinct().collect(Collectors.toList());
    }

}
