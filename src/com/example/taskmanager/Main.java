package com.example.taskmanager;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.enums.Priority;
import com.example.taskmanager.model.enums.TaskStatus;
import com.example.taskmanager.repository.InMemoryTaskRepository;
import com.example.taskmanager.repository.InMemoryUserRepository;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.UserService;

public class Main {
    public static void main(String[] args) {
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();

        UserService userService = new UserService(userRepository);
        TaskService taskService = new TaskService(taskRepository,userRepository);

        User alice = userService.createUser("Alice");
        User bob = userService.createUser("Bob");

        Task task1 = taskService.createTask("Implement login",
                "Create login functionality",
                Priority.HIGH);

        Task task2 = taskService.createTask( "Fix bug",
                "Fix critical production bug",
                Priority.MEDIUM);

        taskService.assignTaskToUser(task1.getId(), alice.getId());
        taskService.assignTaskToUser(task2.getId(), bob.getId());

        taskService.changeTaskStatus(task1.getId(), TaskStatus.IN_PROGRESS);
        taskService.changeTaskStatus(task2.getId(), TaskStatus.DONE);

        System.out.println("All users:");
        userService.getAllUsers()
                .forEach(System.out::println);
    }
}
