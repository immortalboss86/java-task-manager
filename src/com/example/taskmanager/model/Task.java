package com.example.taskmanager.model;

import com.example.taskmanager.model.enums.Priority;
import com.example.taskmanager.model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private final UUID id;
    private final String title;
    private final String description;
    private final LocalDateTime createdAt;

    private TaskStatus status;
    private Priority priority;
    private UUID assignedUserId;

    public Task(
            UUID id,
            String title,
            String description,
            Priority priority) {
        this.id = Objects.requireNonNull(id, " id cannot be null");
        this.title = Objects.requireNonNull(title, " title cannot be null");
        this.description = description;
        this.priority = Objects.requireNonNull(priority, " priority cannot be null");
        this.status = TaskStatus.NEW;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public UUID getAssignedUserId() {
        return assignedUserId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void assignToUser(UUID userId) {
        this.assignedUserId = Objects.requireNonNull(userId, "userId must not be null");
    }

    public void changeStatus(TaskStatus newStatus) {
        this.status = Objects.requireNonNull(newStatus, "status must not be null");
    }

    public void changePriority(Priority newPriority) {
        this.priority = Objects.requireNonNull(newPriority, "priority must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
       if(!(o instanceof Task task)) return false;
       return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
