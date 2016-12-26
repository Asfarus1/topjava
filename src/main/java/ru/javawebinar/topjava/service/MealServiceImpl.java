package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository = new InMemoryMealRepositoryImpl();

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId),id);
    }

    @Override
    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id,userId),id);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, int caloriesPerDay) {
        return  MealsUtil.getWithExceeded(repository.getAll(userId),caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getDuringPeriod(LocalDate startDate, LocalDate endDate, int userId, int caloriesPerDay) {
        return MealsUtil.getWithExceeded(repository.getDuringPeriod(startDate,endDate,userId) ,caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getDuringPeriod(LocalTime startTime, LocalTime endTime, int userId, int caloriesPerDay) {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(userId), startTime, endTime, caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getDuringPeriod(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int userId, int caloriesPerDay) {
        return MealsUtil.getFilteredWithExceeded(repository.getDuringPeriod(startDate,endDate,userId), startTime, endTime, caloriesPerDay);
    }
}
