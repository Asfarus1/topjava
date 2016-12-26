package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public List<Meal> getDuringPeriod(LocalDate startDate, LocalDate endDate, int userId) {
        return getMealRepositoryForAutorizedUser(userId)
                .values()
                .stream()
                .filter(u->DateTimeUtil.isBetween(u.getDate(), startDate, endDate))
                .sorted((m1,m2)-> m1.getDateTime().compareTo(m2.getDateTime()))
                .collect(Collectors.toList());
    }

    private Map<Integer,Meal> getMealRepositoryForAutorizedUser(int userId){
        Map<Integer,Meal> result = repository.get(userId);
        if (result == null){
            result = new ConcurrentHashMap<>();
            repository.put(userId,result);
        }
        return result;
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        getMealRepositoryForAutorizedUser(meal.getUser().getId()).put(meal.getId(),meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return getMealRepositoryForAutorizedUser(userId).remove(id)!=null;
    }

    @Override
    public Meal get(int id,int userId) {
        return getMealRepositoryForAutorizedUser(userId).get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getDuringPeriod(LocalDate.MIN,LocalDate.MAX,userId);
    }
}

