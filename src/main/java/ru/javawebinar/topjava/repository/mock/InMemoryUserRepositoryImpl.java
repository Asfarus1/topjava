package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer,User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.USERS.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id)!=null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()){
            if (repository.values().stream().filter(u->u.getName().equals(user.getName())).findAny().isPresent()) {
                throw new RuntimeException("User name already exists!");
            }
            if (repository.values().stream().filter(u->u.getEmail().equals(user.getEmail())).findAny().isPresent()) {
                throw new RuntimeException("User email already exists!");
            }
            user.setId(counter.incrementAndGet());
        }
        return repository.put(user.getId(),user);
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return repository.values().stream().sorted((u1, u2) -> u2.getName().compareTo(u1.getName())).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(java.lang.String email) {
        LOG.info("getByEmail " + email);
        return repository.values().stream().filter(user -> email.equals(user.getEmail())).findAny().orElse(null);
    }
}
