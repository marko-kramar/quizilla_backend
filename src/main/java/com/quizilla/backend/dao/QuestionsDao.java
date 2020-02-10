package com.quizilla.backend.dao;

import com.quizilla.backend.model.Answer;
import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Question;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.quizilla.backend.util.QuizillaUtil.*;

@Repository
public class QuestionsDao {
    private static final List<Question> questions = new ArrayList<>();

    static {
        questions.add(new Question(getPositiveLong(), "What is the capital of Croatia?", getCategory(1L), Arrays.asList(
                new Answer(1L, "Berlin"),
                new Answer(2L, "Zagreb"),
                new Answer(3L, "Budapest"),
                new Answer(4L, "Sarajevo")
        ), 2L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "How many natural satellites does the Earth have?", getCategory(3L), Arrays.asList(
                new Answer(1L, "1"),
                new Answer(2L, "2"),
                new Answer(3L, "3"),
                new Answer(4L, "4")
        ), 1L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "How many continents does the Earth have?", getCategory(1L), Arrays.asList(
                new Answer(1L, "5"),
                new Answer(2L, "6"),
                new Answer(3L, "7"),
                new Answer(4L, "11")
        ), 3L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "Where does U2 music band come from?", getCategory(2L), Arrays.asList(
                new Answer(1L, "Germany"),
                new Answer(2L, "Croatia"),
                new Answer(3L, "Netherlands"),
                new Answer(4L, "Ireland")
        ), 4L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "Which US president was killed by rifle shot in Dallas, 1963?", getCategory(4L), Arrays.asList(
                new Answer(1L, "Franklin D. Roosevelt"),
                new Answer(2L, "George W. Bush"),
                new Answer(3L, "John F. Kennedy"),
                new Answer(4L, "Abraham Lincoln")
        ), 3L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "What is Nintendo's most popular franchise?", getCategory(5L), Arrays.asList(
                new Answer(1L, "Star Wars"),
                new Answer(2L, "Super Mario"),
                new Answer(3L, "Avengers"),
                new Answer(4L, "Doctor Who")
        ), 2L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "'To be, or not to be, that is the question.' This line is from which Shakespeare novel?", getCategory(6L), Arrays.asList(
                new Answer(1L, "Hamlet"),
                new Answer(2L, "Othello"),
                new Answer(3L, "King Lear"),
                new Answer(4L, "Romeo and Juliet")
        ), 1L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "What is the name of Microsoft's gaming console series?", getCategory(5L), Arrays.asList(
                new Answer(1L, "PlayStation"),
                new Answer(2L, "GameCube"),
                new Answer(3L, "Sega MegaDrive"),
                new Answer(4L, "Xbox")
        ), 4L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "What is the name of the star in our own Solar system?", getCategory(5L), Arrays.asList(
                new Answer(1L, "Proxima Centauri"),
                new Answer(2L, "Jupiter"),
                new Answer(3L, "Sun"),
                new Answer(4L, "Earth")
        ), 3L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "Where does the famous philosopher Socrates come from?", getCategory(4L), Arrays.asList(
                new Answer(1L, "Ancient Greece"),
                new Answer(2L, "China"),
                new Answer(3L, "Rome"),
                new Answer(4L, "Russia")
        ), 1L, getEnglish()));

        questions.add(new Question(getPositiveLong(), "'Dancing Queen' is pop music hit from which band?", getCategory(2L), Arrays.asList(
                new Answer(1L, "Beatles"),
                new Answer(2L, "Guns'n'Roses"),
                new Answer(3L, "ABBA"),
                new Answer(4L, "Psihomodo Pop")
        ), 3L, getEnglish()));
    }


    public List<Question> findAllQuestions() {
        return questions;
    }

    public List<Question> findQuestionsByCategory(final String categoryName) {
        return questions.parallelStream().filter(question -> question.getCategory().getName().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
    }

    public Question findRandomQuestion() {
        return questions.get(new Random().nextInt(questions.size()));
    }

    public List<Category> findCategories() {
        return questions.parallelStream().map(Question::getCategory).distinct().sorted(Comparator.comparing(Category::getId)).collect(Collectors.toList());
    }

}
