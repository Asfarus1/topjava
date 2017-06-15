package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.dto.MealWithExceed;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository{

    AtomicLong key = new AtomicLong(0);
    private Map<Long, Map<Long, Meal>> meals = new ConcurrentHashMap<>();

    private Map<Long, Meal> getUserMeals(Long userId){
        return meals.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
    }

    @Override
    public Meal get(long mealId, long userId) {
        Map<Long,Meal> userMeals = meals.get(userId);
        if (userMeals != null){
            return userMeals.get(mealId);
        }
        return null;
    }

    @Override
    public Meal save(Meal meal) {
        User user = meal.getUser();
        if (user != null){
            if (meal.getId() == 0){
                meal.setId(key.incrementAndGet());
            }
            return meals.computeIfAbsent(user.getId(), k -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
        }
        return null;
    }

    @Override
    public Meal remove(long mealId, long userId) {
        Map<Long,Meal> userMeals = meals.get(userId);
        if (userMeals != null){
            return userMeals.remove(mealId);
        }
        return null;
    }

    @Override
    public List<MealWithExceed> getAll(long userId, int caloriesPerDay) {
        return MealsUtil.getFilteredWithExceeded(getUserMeals(userId).values() ,LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getFilteredMeals(long userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return MealsUtil.getFilteredWithExceeded(
                getUserMeals(userId).values()
                        .stream()
                        .filter(m -> DateTimeUtil.isBetween(m.getDate(), startDate, endDate))
                        .collect(Collectors.toList()),
                LocalTime.MIN, LocalTime.MAX, caloriesPerDay
        ).stream()
                .sorted((m1,m2)->m1.getDateTime() == null ? -1 : m1.getDateTime().compareTo(m2.getDateTime()))
                .collect(Collectors.toList());
    }
}
