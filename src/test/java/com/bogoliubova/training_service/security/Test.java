package com.bogoliubova.training_service.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
 class  JwtServiceIntegrationTest {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void testExtractUserName() {
        // Создаем UserDetails
        UserDetails userDetails = User.withUsername("testUser")
                .password("password")
                .roles("USER")
                .build();

        // Генерируем токен на основе UserDetails
        String token = jwtService.generateToken(userDetails);

        // Извлекаем имя пользователя из токена
        String extractedUsername = jwtService.extractUserName(token);

        // Проверяем, что извлеченное имя пользователя совпадает с ожидаемым именем пользователя
        assertEquals(userDetails.getUsername(), extractedUsername);
    }

    @Test
    void testGenerateToken() {
        UserDetails userDetails = new User("testUser", "password", null);
        String token = jwtService.generateToken(userDetails);

        assertTrue(isTokenValid(token, userDetails));
    }

//    @Test
//    void testIsTokenValid() {
//        UserDetails userDetails = new User("testUser", "password", null);
//        String token = generateToken(userDetails);
//
//        assertTrue(jwtService.isTokenValid(token, userDetails));

//        UserDetails userDetails = User.withUsername("testUser")
//                .password("password")
//                .roles("USER")
//                .build();
//        String token = generateToken(String.valueOf(userDetails));
//        assertTrue(jwtService.isTokenValid(token, userDetails));

  //  }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(getSigningKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = jwtService.extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = jwtService.extractExpiration(token);
        return expiration.before(new Date());
    }

    private Key getSigningKey() {
        byte[] keyBytes = Keys.hmacShaKeyFor(jwtSigningKey.getBytes()).getEncoded();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
