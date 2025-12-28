package com.example.taskmanager.service;

import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.exception.UserNotFoundException;
import com.example.taskmanager.exception.ValidationException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.enums.Priority;
import com.example.taskmanager.model.enums.TaskStatus;
import com.example.taskmanager.repository.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskService {

    private final Repository<Task, UUID> taskRepository;
    private final Repository<User, UUID> userRepository;

    public TaskService(Repository<Task, UUID> taskRepository,
                       Repository<User, UUID> userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(String title, String description, Priority priority) {
        if(title == null || title.isBlank()){
            throw new ValidationException("Title cannot be blank");
        }
        if(priority == null){
            throw new ValidationException("Priority cannot be null");
        }

        Task task = new Task(UUID.randomUUID(),
                title,
                description,
                priority);

        taskRepository.save(task);
        return task;
    }

    public void assignTaskToUser(UUID taskId,UUID userId) {
        Task task = getTaskById(taskId);
        User user = getUserById(userId);

        task.assignToUser(user.getId());
        taskRepository.save(task);
    }

    public void changeTaskStatus(UUID taskId, TaskStatus status) {
        Task task = getTaskById(taskId);
        task.changeStatus(status);
        taskRepository.save(task);
    }

    public void changeTaskPriority(UUID taskId, Priority priority) {
        Task task = getTaskById(taskId);
        task.changePriority(priority);
        taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUser(UUID userId){
        getUserById(userId);

        return taskRepository.findAll().stream()
                .filter(task -> userId.equals(task.getAssignedUserId()))
                .collect(Collectors.toList());
    }

    public Task getTaskById(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException(taskId));
    }
    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->
                        new UserNotFoundException(userId));
    }
}
