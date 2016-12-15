package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealWithExceedRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by asfarus on 14.12.2016.
 */
public class MealWithExceedService implements MealWithExceedRepository {
    private static final int MAX_CALORIES_PER_DAY = 2000;
    private final MealRepository mealRepository;

    public MealWithExceedService() {
        this.mealRepository = new MealService();
    }

    @Override
    public List<MealWithExceed> getAll() {
        return MealsUtil.getFilteredWithExceeded(mealRepository.getAll(), LocalTime.MIN,LocalTime.MAX, MAX_CALORIES_PER_DAY);
    }

    @Override
    public MealWithExceed getById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(MealWithExceed entity) {

    }

    @Override
    public void add(MealWithExceed entity) {

    }
}
