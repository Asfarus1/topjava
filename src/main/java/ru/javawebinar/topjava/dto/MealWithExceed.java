package ru.javawebinar.topjava.dto;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.NamedEntity;

import java.time.LocalDateTime;

public class MealWithExceed extends NamedEntity{

    private int calories;

    private LocalDateTime dateTime;

    private boolean exceed;

    public MealWithExceed() {
    }

    public MealWithExceed(Meal meal, boolean exceed) {
        this.setId(meal.getId());
        this.setDescription(meal.getDescription());
        this.setCalories(meal.getCalories());
        this.setDateTime(meal.getDateTime());
        this.exceed = exceed;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setExceed(boolean exceed) {
        this.exceed = exceed;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "id=" + getId() +
                ", dateTime=" + getDateTime() +
                ", description='" + getDescription() + '\'' +
                ", calories=" + getCalories() +
                ", exceed=" + exceed +
                '}';
    }
}
