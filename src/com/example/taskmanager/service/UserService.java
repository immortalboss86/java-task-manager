package com.example.taskmanager.service;

import com.example.taskmanager.exception.UserNotFoundException;
import com.example.taskmanager.exception.ValidationException;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.Repository;

import java.util.List;
import java.util.UUID;

public class UserService {

    private final Repository<User, UUID> userRepository;

    public UserService(Repository<User, UUID> userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name){
        if(name == null || name.isBlank()){
            throw new ValidationException("Username must not be empty");
        }

        User user = new User (UUID.randomUUID(), name);
        userRepository.save(user);
        return user;
    }

    public User getUserById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(()->
                        new UserNotFoundException(id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
