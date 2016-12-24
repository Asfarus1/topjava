package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal save(Meal meal);

    void delete(int id,int userId);

    Meal get(int id,int userId);

    List<MealWithExceed> getAll(int userId, int caloriesPerDay);

    List<MealWithExceed> getDuringPeriod(LocalDate startDate, LocalDate endDate,int userId, int caloriesPerDay);

    List<MealWithExceed> getDuringPeriod(LocalTime startTime, LocalTime endTime, int userId, int caloriesPerDay);

    List<MealWithExceed> getDuringPeriod(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int userId, int caloriesPerDay);
}
