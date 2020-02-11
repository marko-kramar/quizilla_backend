package com.quizilla.backend.util;

import com.quizilla.backend.model.Category;
import com.quizilla.backend.model.Language;

import java.util.*;

public class QuizillaUtil {
    private static List<Category> categories = new ArrayList<>();

    /*static {
        categories.add(new Category(1L, "Geography"));
        categories.add(new Category(2L, "Music"));
        categories.add(new Category(3L, "Astronomy"));
        categories.add(new Category(4L, "Famous people"));
        categories.add(new Category(5L, "Gaming"));
        categories.add(new Category(6L, "Literature"));
    }*/

    /*public static Language getEnglish() {
        return new Language(1L, "en", "English");
    }*/

    public static Long getPositiveLong() {
        return Math.abs(new Random().nextLong());
    }

    public static Category getCategory(Long id) {
        return categories.parallelStream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
    }
}
