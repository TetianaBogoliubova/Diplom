package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.entity.User;
import com.bogoliubova.training_service.entity.enums.AllRoles;
import com.bogoliubova.training_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    private final User user = new User();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);

        user.setUserId(UUID.fromString("226e8867-e33a-2cd3-f362-211620192111"));
        user.setLogin("user");
        user.setPassword("111");
        user.setRole(AllRoles.ROLE_USER);
    }

    //проверяет метод save в контексте сохранения существующего пользователя
    @Test
    void savePositiveTest() {
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void createExistUserTest() {
        when(userRepository.existsByLogin(user.getLogin())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> userService.create(user));

        verify(userRepository, never()).save(user);
    }

    // проверяет создание нового пользователя в случае отсутствия пользователя с таким логином в системе.
    @Test
    void createNotExistUserTest() {
        User existingUser = new User();
        existingUser.setLogin("newUser");
        existingUser.setPassword("999");
        existingUser.setRole(AllRoles.ROLE_USER);

        when(userRepository.existsByLogin(existingUser.getLogin())).thenReturn(false);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User createdUser = userService.create(existingUser);

        assertEquals(existingUser, createdUser);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void getByUsernamePositiveTest() {
        when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

        User expectedUser = userService.getByUsername(user.getLogin());

        assertEquals(user, expectedUser);
    }

    @Test
    void getByUsernameNegativeTest() {
        String login = "newLogin";
        when(userRepository.findByLogin(login)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getByUsername(login));
    }

    // проверка на возвращает ссылки на функцию getByUsername
    @Test
    void userDetailsServiceTest() {
        assertNotNull(userService.userDetailsService());
    }
}
