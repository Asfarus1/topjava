package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by asfarus on 14.12.2016.
 */
public class MealService implements MealRepository {
    private Map<Integer,Meal> meals;

    public MealService() {
       meals = Stream.of(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        ).collect(Collectors.toMap(Meal::getId,meal -> meal));
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> res = new ArrayList<>(meals.size());
        res.addAll(meals.values());
        return Collections.unmodifiableList(res);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public void deleteById(int id) {
        meals.remove(id);
    }

    @Override
    public void update(Meal entity) {
        meals.put(entity.getId(),entity);
    }

    @Override
    public void add(Meal entity) {
        meals.put(entity.getId(),entity);
    }
}
