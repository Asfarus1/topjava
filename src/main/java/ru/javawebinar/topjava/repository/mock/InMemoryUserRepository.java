package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

/**
 * Created by asfarus on 15.06.2017.
 */
public class InMemoryUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public User load(long id) {
        return null;
    }
}
