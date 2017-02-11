package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by asfarus on 11.02.2017.
 */
public abstract class AbstractMealController {

    @Autowired
    protected MealService mealService;

    protected final Logger log = LoggerFactory.getLogger(getClass().getName());

    protected Meal get(int id){
        final int userId = AuthorizedUser.id();
        log.info("get meal with id {} by user {}", id, userId);
        return mealService.get(id, userId);
    }

     protected void delete(int id){
        final int userId = AuthorizedUser.id();
        log.info("delete meal with id {} by user {}", id, userId);
        mealService.delete(id, userId);
    }

    protected List<MealWithExceed>getBetweenDateTimes(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        final int userId = AuthorizedUser.id();
        log.info("get meals between {} and {} dates and between {} and {} times by user", startDate, endDate, startTime, endTime, userId);
        return MealsUtil.getFilteredWithExceeded(mealService.getBetweenDates(startDate, endDate, userId), startTime, endTime, AuthorizedUser.getCaloriesPerDay());
    }

    protected List<MealWithExceed> getAll(){
        final int userId = AuthorizedUser.id();
        log.info("get all by user {}", userId);
        return MealsUtil.getWithExceeded(mealService.getAll(userId),AuthorizedUser.getCaloriesPerDay());
    }

    protected Meal update(Meal meal){
        final int userId = AuthorizedUser.id();
        log.info("update meal with id {} by user {}", meal.getId(), userId);
        return mealService.update(meal, userId);
    }

    protected Meal save(Meal meal){
        final int userId = AuthorizedUser.id();
        log.info("save meal by user {}", userId);
        return mealService.save(meal, userId);
    }


}
