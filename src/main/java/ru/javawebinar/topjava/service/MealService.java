package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by asfarus on 14.12.2016.
 */
public class MealService{
    private Map<Integer,Meal> meals = new HashMap<>();
    private final static AtomicInteger idGenerator = new AtomicInteger(0);

    {
        update(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        update(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        update(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        update(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        update(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        update(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public MealService() {
    }

    public List<MealWithExceed> getAll() {
        List<Meal> list = meals.values().stream().collect(Collectors.toList());
        return getFilteredWithExceeded(list,LocalTime.MIN,LocalTime.MAX,2000);
    }

    public Meal get(int id) {
        return meals.get(id);
    }

    public void delete(int id) {
        meals.remove(id);
    }

    public void update(Meal meal) {
        if (meal.getId() == 0)
            meal.setId(idGenerator.incrementAndGet());
        meals.put(meal.getId(),meal);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded,meal.getId());
    }
}
