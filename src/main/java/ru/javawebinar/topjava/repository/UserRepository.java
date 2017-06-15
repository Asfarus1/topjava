package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

public interface UserRepository {
    User save(User user);

    User load(long id);

}
