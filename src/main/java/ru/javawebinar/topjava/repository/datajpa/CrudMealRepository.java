package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gkislin
 * 02.10.2016
 */
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    List<Meal> findByUserOrderByDateTime(User user);

    @Override
    void delete(Meal meal);

    @Override
    Meal save(Meal meal);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE u.id:=id AND u.user.id=:userId")
    Meal findOne(@Param("id") int id,@Param("userId") int userId);

    List<Meal> findByUserAndDateTimeBetweenOrderByDateTime(User user, LocalDateTime startDate, LocalDateTime endDate);

    @Modifying
    @Query("DELETE FROM Meal m WHERE u.id=:id AND u.user.id=:userId")
    int delete(@Param("id") int id,@Param("userId") int userId);
}
