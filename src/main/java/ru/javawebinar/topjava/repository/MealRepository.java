package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealRepository {
    Meal get(long mealId, long userId);

    Meal save(Meal meal);

    Meal remove(long mealId, long userId);

    List<MealWithExceed> getAll(long userId, int caloriesPerDay);

    List<MealWithExceed> getFilteredMeals(long userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int caloriesPerDay);
}
