package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.entity.User;
import com.bogoliubova.training_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            // Заменить на свои исключения
            throw new RuntimeException("A user with this name already exists");
        }
        return save(user);
    }

    //Получение пользователя по имени пользователя
    public User getByUsername(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Получение пользователя по имени пользователя Нужен для Spring Security
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

}

