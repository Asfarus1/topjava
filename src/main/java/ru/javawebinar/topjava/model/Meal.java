package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal extends NamedEntity{
    private final LocalDateTime dateTime;

    private final int calories;

    private final User user;

    public Meal(LocalDateTime dateTime, String name, int calories,User user) {
        this(null, dateTime, name, calories, user);
    }

    public User getUser() {
        return user;
    }

    public Meal(Integer id, LocalDateTime dateTime, String name, int calories, User user) {
        super(id,name);
        this.dateTime = dateTime;

        this.calories = calories;
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
