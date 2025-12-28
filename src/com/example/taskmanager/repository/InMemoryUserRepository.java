package com.example.taskmanager.repository;

import com.example.taskmanager.model.User;

import java.util.*;

public class InMemoryUserRepository implements Repository<User, UUID> {

    private final Map<UUID, User> storage = new HashMap<>();



    @Override
    public void save(User user) {
        storage.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
