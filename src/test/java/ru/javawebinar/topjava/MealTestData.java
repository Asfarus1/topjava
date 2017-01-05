package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.util.Objects;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId()) &&
                            Objects.equals(expected.getDescription(), actual.getDescription()) &&
                            Objects.equals(expected.getCalories(), actual.getCalories()) &&
                            Objects.equals(expected.getDateTime(), actual.getDateTime()))
    );
}
