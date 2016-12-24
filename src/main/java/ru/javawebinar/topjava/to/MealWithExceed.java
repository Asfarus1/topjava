package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.NamedEntity;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed extends NamedEntity{

    private final LocalDateTime dateTime;

    private final int calories;

    private final boolean exceed;

    public MealWithExceed(Integer id, LocalDateTime dateTime, String name, int calories, boolean exceed) {
        super(id,name);
        this.dateTime = dateTime;
        this.calories = calories;
        this.exceed = exceed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
