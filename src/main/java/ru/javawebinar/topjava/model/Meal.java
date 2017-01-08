package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id and m.user=:userId"),
        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m " +
                "SET m.dateTime=:dateTime," +
                "m.description=:description," +
                "m.calories=:calories " +
                "WHERE m.id=:id and m.user=:userId"),
        @NamedQuery(name = Meal.ALL_SORTED_BETWEEN_DATES, query = "SELECT m " +
                "FROM Meal m " +
//                "LEFT JOIN FETCH m.user " +
                "WHERE m.user=?1 and m.dateTime BETWEEN ?2 and ?3 " +
                "ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m " +
                "FROM Meal m " +
//                "LEFT JOIN FETCH m.user " +
                "WHERE m.user.id=?1 " +
                "ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id=:id and m.user=:userId"),
})

@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String UPDATE = "Meal.update";
    public static final String GET = "Meal.get";
    public static final String ALL_SORTED_BETWEEN_DATES = "Meal.allSortedBetweenDates";
    public static final String ALL_SORTED = "Meal.allSorted";

    @Column(name = "date_time")
    @NotEmpty
    private LocalDateTime dateTime;

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "calories")
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
