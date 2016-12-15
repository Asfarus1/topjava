package ru.javawebinar.topjava.repository;

import java.util.List;

/**
 * Created by asfarus on 14.12.2016.
 */
public interface BaseRepository<M> {
    List<M> getAll();
    M getById(int id);
    void deleteById(int id);
    void update(M entity);
    void add(M entity);
}
