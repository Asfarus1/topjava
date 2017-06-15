package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealService {

    private MealRepository mealRepository;
    {
        Arrays.stream(new Meal[]{
                        new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                        new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                        new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                        new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                        new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                        new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
                }
        ).forEach(this::save);
    }


    public List<MealWithExceed> getAll(long userId, int caloriesPerDay){
        return mealRepository.getAll(userId, caloriesPerDay);
    }

    public void save(Meal meal){
        mealRepository.save(meal);
    }

    public Meal get(long mealId, long userId){
        return ValidationUtil.checkNotFoundWithId(mealRepository.get(mealId, userId),mealId);
    }

    public void remove(long mealId, long userId) {
        ValidationUtil.checkNotFoundWithId(mealRepository.remove(mealId, userId),mealId);
    }
}