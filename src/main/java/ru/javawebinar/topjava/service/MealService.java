package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;

public class MealService {

    AtomicLong key = new AtomicLong(0);
    private Map<Long, Meal> meals = new ConcurrentHashMap<>();

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

    public List<MealWithExceed> getAll(){
        return getFilteredWithExceeded(meals.values(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    public void save(Meal meal){
        if (meal.getId() == 0){
            meal.setId(key.incrementAndGet());
        }
        meals.put(meal.getId(), meal);
    }

    public Meal get(long id){
        return meals.get(id);
    }

    public void remove(long id) {
        meals.remove(id);
    }
}