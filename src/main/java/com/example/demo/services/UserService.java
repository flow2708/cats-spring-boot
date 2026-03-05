package com.example.demo.services;

import com.example.demo.PasswordBCrypt;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public boolean register(User user, Model model) {
        // Проверяем, существует ли уже пользователь
        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Этот email уже привязан к другому пользователю");
        }

        user.setPassword(PasswordBCrypt.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public User login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }
}
