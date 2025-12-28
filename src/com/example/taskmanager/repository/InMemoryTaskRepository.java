package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;

import java.util.*;

public class InMemoryTaskRepository implements Repository <Task, UUID>{


    private final Map<UUID, Task> storage = new HashMap<>();

    @Override
    public void save(Task task) {
        storage.put(task.getId(), task);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
