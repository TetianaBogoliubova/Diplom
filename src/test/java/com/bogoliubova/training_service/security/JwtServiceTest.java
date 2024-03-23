package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.entity.User;
import com.bogoliubova.training_service.entity.enums.AllRoles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class JwtServiceTest {

    @Mock
    private UserDetails userDetails;
    @Mock
    JwtService jwtService;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
       // MockitoAnnotations.openMocks(this);
        // jwtService = spy(new JwtService()); // Создаем шпиона для объекта JwtService
    }

//
//    @Test
//    public void testExtractUserName() {
//         jwtService = Mockito.mock(JwtService.class);
//
//        String token = "exampleToken";
//        Claims claims = Mockito.mock(Claims.class);
//        Mockito.when(jwtService.extractAllClaims(token)).thenReturn(claims);
//        Mockito.when(claims.getSubject()).thenReturn("testUser");
//
//        String userName = jwtService.extractUserName(token);
//
//        assertEquals("testUser", userName);
//    }
//
//    @Test
//    public void testExtractUserName99() {
//        JwtService jwtService = new JwtService();
//        String token = "your_jwt_token_here";
//
//        Claims mockClaims = Mockito.mock(Claims.class);
//        Mockito.when(mockClaims.getSubject()).thenReturn("testUser");
//
//        JwtService spyJwtService = Mockito.spy(jwtService);
//        Mockito.doReturn(mockClaims).when(spyJwtService).extractAllClaims(token);
//
//        String userName = spyJwtService.extractUserName(token);
//
//        assertEquals("testUser", userName);
//    }


//    @Test
//    void testExtractUserName() {
//        String token = "1234567890";
//        when(jwtService.extractAllClaims(any())).thenReturn(Mockito.mock(Claims.class));
//    }

//    @Test
//    void testExtractUserName88() {
//        Claims claimsMock = Mockito.mock(Claims.class);
//        // Мокируем extractAllClaims для возвращения заглушки
//      //  when(jwtService.extractAllClaims(any())).thenReturn(claimsMock);//Mockito.mock(Claims.class));
//
//        // Тестируемый токен
//        String token = "testToken";
//
//        // Перехватываем вызов extractClaim и возвращаем фиктивное значение
//      //  when(jwtService.extractClaim(any(), any())).thenAnswer(invocation -> {
//            Function<Claims, String> function = invocation.getArgument(1);
//            //Claims claims = Mockito.mock(Claims.class);
//            Claims claims = invocation.getArgument(0);
//            when(claims.getSubject()).thenReturn("testUser");
//            return function.apply(claims);
//        });

    // Вызываем метод, который мы тестируем
//        String extractedUserName = jwtService.extractUserName(token);
//
//        // Проверяем, что метод вернул ожидаемый результат
//        assertEquals("testUser", extractedUserName);
//    }
    @Test
    void testExtractUserName1() {
        // Создание тестового токена
        String token = Jwts.builder()
                .setSubject("testUser")
                .signWith(Keys.hmacShaKeyFor("secret".getBytes()))
                .compact();

        JwtService jwtService = new JwtService();

        // Установка значения jwtSigningKey для тестирования
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "secret");

        // Вызов тестируемого метода
        String extractedUserName = jwtService.extractUserName(token);

        // Проверка
        assertEquals("testUser", extractedUserName);
    }


    @Test
    void testExtractUserName2() {
        // Создание тестового токена
        String token = Jwts.builder()
                .setSubject("testUser")
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)) // Используем стандартный безопасный ключ
                .compact();

        JwtService jwtService = new JwtService();

        // Установка значения jwtSigningKey для тестирования
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "secret");

        // Вызов тестируемого метода
        String extractedUserName = jwtService.extractUserName(token);

        // Проверка
        assertEquals("testUser", extractedUserName);
    }

    @Test
    void testExtractUserName3() {
        // Создание тестового токена
        String token = Jwts.builder()
                .setSubject("testUser")
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)) // Используем стандартный безопасный ключ для HS256
                .compact();

        JwtService jwtService = new JwtService();

        // Установка значения jwtSigningKey для тестирования
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "secret");

        // Вызов тестируемого метода
        String extractedUserName = jwtService.extractUserName(token);

        // Проверка
        assertEquals("testUser", extractedUserName);
    }


    @Test
    void testExtractUserName4() {
        // Создание тестового токена
        String token = Jwts.builder()
                .setSubject("testUser")
                .signWith(Keys.hmacShaKeyFor("secret".getBytes())) // Создание безопасного ключа для HS256
                .compact();

        JwtService jwtService = new JwtService();

        // Установка значения jwtSigningKey для тестирования
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "secret");

        // Вызов тестируемого метода
        String extractedUserName = jwtService.extractUserName(token);

        // Проверка
        assertEquals("testUser", extractedUserName);
    }

    @Test
    void testGenerateToken1() {
        // Создание mock UserDetails
        UserDetails userDetails = mock(User.class);
        when(userDetails.getUsername()).thenReturn("testUser");

        // Создание JwtService
        JwtService jwtService = new JwtService();

        // Установка значения jwtSigningKey для тестирования
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "secret");

        // Вызов тестируемого метода
        String generatedToken = jwtService.generateToken(userDetails);

        // Проверка
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor("secret".getBytes()))
                .build()
                .parseClaimsJws(generatedToken)
                .getBody();

        assertEquals("testUser", claims.getSubject());
    }

    @Test
    void extractUserName() {
    }

    @Test
    void generateToken() {
    }

    @Test
    void isTokenValid() {
    }

    //////////////////////////////////

//    @Test
//    void testExtractUserName() {
//        JwtService jwtService = new JwtService();
//        String token = "your_test_token_here";
//        String extractedUserName = jwtService.extractUserName(token);
//        assertNotNull(extractedUserName);
//        // Добавьте дополнительные проверки при необходимости
//    }

    @Test
    void testGenerateToken() {
        JwtService jwtService = new JwtService();

        User user = new User();
        // user.setLogin(request.getLogin());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(AllRoles.ROLE_USER);

//        UserDetails userDetails = User.withUsername("testUser")
//                .username("testUser")
//                .password("password")
//                .roles("USER")
//                .build();
//        String generatedToken = jwtService.generateToken(userDetails);
//        assertNotNull(generatedToken);


        String generatedToken = jwtService.generateToken(userDetails);
        assertNotNull(generatedToken);
        // Добавьте дополнительные проверки при необходимости
    }
    // Добавьте дополнительные проверки при необходимости


//    @Test
//    void testIsTokenValid() {
//        JwtService jwtService = new JwtService();
//       // UserDetails userDetails = User.builder()
//          //      .username("testUser")
//                .password("password")
//                .roles("USER")
//                .build();
//        String token = jwtService.generateToken(userDetails);
//        assertTrue(jwtService.isTokenValid(token, userDetails));
//        // Добавьте дополнительные проверки при необходимости


    @Test
    void testIsTokenExpired() {
        JwtService jwtService = new JwtService();
        String token = "your_test_token_here";
        //  boolean isExpired = jwtService.isTokenExpired(token);
        // Добавьте проверки, соответствующие тесту на просроченность токена
    }

    @Test
    void testExtractExpiration() {
        JwtService jwtService = new JwtService();
        String token = "your_test_token_here";
        //   Date expirationDate = jwtService.extractExpiration(token);
        // Добавьте проверки, соответствующие тесту на извлечение даты истечения токена
    }

    @Test
    void testExtractAllClaims() {
        JwtService jwtService = new JwtService();
        String token = "your_test_token_here";
        // Добавьте проверки, соответствующие тесту на извлечение всех данных из токена
    }

    @Test
    void testGetSigningKey() {
        JwtService jwtService = new JwtService();
        // Установка значения jwtSigningKey для тестирования
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "your_jwt_signing_key_here");
        // assertNotNull(jwtService.getSigningKey());
        // Добавьте дополнительные проверки при необходимости
    }

    @Test
    void testExtractUserName7() {
        // Создание тестового токена
        String token = Jwts.builder()
                .setSubject("testUser")
                .signWith(Keys.hmacShaKeyFor("secret".getBytes())) // Используем безопасный ключ для тестирования
                .compact();

        JwtService jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "secret");

        // Вызов тестируемого метода
        String extractedUserName = jwtService.extractUserName(token);

        // Проверка
        assertEquals("testUser", extractedUserName);
    }
}

    // Для исправления ошибки WeakKeyException Вам нужно использовать ключ с размером не менее
    // 256 бит для алгоритма HMAC-SHA. Вы можете создать такой ключ с помощью метода Jwts.hmacShaKeyFor()
    // и передать его в метод signWith() при создании JWT. Вот пример исправленного кода:


//    @Test
//    void testExtractUserName7() {
//        // Создание тестового токена
//        String token = Jwts.builder()
//                .setSubject("testUser")
//                .signWith((Key) Jwts.header("1d770d10e025ee9c1918bb89bcfb2c45c77f966e26171365be2d0ad99d32c814".getBytes())) // Замените "your-secure-key" на Ваш секретный ключ
//                .compact();
//
//        JwtService jwtService = new JwtService();
//
//        // Установка значения jwtSigningKey для тестирования
//        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", "secret");
//
//        // Вызов тестируемого метода
//        String extractedUserName = jwtService.extractUserName(token);
//
//        // Проверка
//        assertEquals("testUser", extractedUserName);
//    }

//    @Test
//    void testExtractUserName() {
//        MockitoAnnotations.initMocks(this); // инициализируем моки
//
//        // Задаем поведение мока для метода extractClaim
//        Mockito.when(jwtService.extractClaim(Mockito.anyString(), Mockito.any()))
//                .thenAnswer(invocation -> {
//                    // Получаем аргументы метода
//                    String token = invocation.getArgument(0);
//                    Function<Claims, String> claimsResolver = invocation.getArgument(1);
//
//                    // Создаем фиктивные Claims (подделанный объект для теста)
//                    Claims claims = Mockito.mock(Claims.class);
//                    // Задаем поведение для метода getSubject() фиктивных Claims
//                    Mockito.when(claims.getSubject()).thenReturn("testUser");
//
//                    // Возвращаем результат вызова функции с фиктивными Claims
//                    return claimsResolver.apply(claims);
//                });

        // Вызываем тестируемый метод
//        String extractedUserName = jwtService.extractUserName("dummyToken");
//
//        // Проверяем, что метод extractUserName вернул ожидаемый результат
//        assertEquals("testUser", extractedUserName);
//    }
//}
  // Замените "your-secure-key" на Ваш секретный ключ длиной не менее 256 бит. Это позволит создать ключ,
// который будет безопасным для использования с алгоритмом HMAC-SHA.