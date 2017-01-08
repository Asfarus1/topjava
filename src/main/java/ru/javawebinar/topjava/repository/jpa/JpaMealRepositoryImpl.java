package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()){
            em.persist(meal);
        }else {
            if (em.createNamedQuery(Meal.UPDATE)
                    .setParameter("id",meal.getId())
                    .setParameter("userId",getMockUser(userId))
                    .setParameter("dateTime", meal.getDateTime())
                    .setParameter("description",meal.getDescription())
                    .setParameter("calories",meal.getCalories())
                    .executeUpdate()==0){
                return null;
            }
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id",id)
                .setParameter("userId",getMockUser(userId))
                .executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        return (Meal) em.createNamedQuery(Meal.GET)
                .setParameter("id",id)
                .setParameter("userId",getMockUser(userId))
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> list =  em.createNamedQuery(Meal.ALL_SORTED)
                .setParameter(1,userId)
                .getResultList();
        return list;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> list =  em.createNamedQuery(Meal.ALL_SORTED_BETWEEN_DATES)
                .setParameter(1,getMockUser(userId))
                .setParameter(2,startDate)
                .setParameter(3,endDate)
                .getResultList();
        return list;
    }

    private User getMockUser(int userId){
        return em.getReference(User.class, userId);
    }
}