package com.backend.back.service;

import com.backend.back.model.User;
import com.backend.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Usuario no encontrado o contraseña incorrecta
    }

    public String getPasswordByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return (user != null) ? user.getPassword() : null;
    }
}
