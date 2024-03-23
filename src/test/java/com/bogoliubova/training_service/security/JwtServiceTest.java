package com.bogoliubova.training_service.security;

import com.bogoliubova.training_service.entity.User;
import com.bogoliubova.training_service.entity.enums.AllRoles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(PowerMockExtension.class)
@PrepareForTest(JwtService.class)
@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
    @Mock
    private UserDetails userDetails;// = mock(UserDetails.class);
    @InjectMocks
    private JwtService jwtService;
    private final User user = new User();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user.setUserId(UUID.fromString("226e8867-e33a-2cd3-f362-211620192111"));
        user.setLogin("user");
        user.setPassword("111");
        user.setRole(AllRoles.ROLE_USER);
    }
        //MockitoAnnotations.initMocks(this);
        // MockitoAnnotations.openMocks(this);
        // jwtService = spy(new JwtService()); // Создаем шпиона для объекта JwtService

    @Test
    void testExtractUserName1() throws Exception {
        // Создание заглушки для JwtService
        JwtService jwtService = PowerMockito.spy(new JwtService());

        // Мокируем приватный метод extractClaim
        PowerMockito.doReturn("testUser").when(jwtService, "extractClaim", anyString(), any(Function.class));

        String token = "exampleToken";
        String actualUsername = jwtService.extractUserName(token);

        assertEquals("testUser", actualUsername);
    }

    @Test
    void testExtractUserName2() throws Exception {
        JwtService jwtService = PowerMockito.spy(new JwtService());

        PowerMockito.doReturn("testUser").when(jwtService, "extractClaim", anyString(), any(Function.class));

        String token = "exampleToken";
        String actualUsername = jwtService.extractUserName(token);

        assertEquals("testUser", actualUsername);
    }

    @Test
    void testExtractUserName3() throws Exception {
        String token = "exampleToken";
        String expectedUsername = "testUser";

        JwtService jwtService = PowerMockito.spy(new JwtService());

        PowerMockito.when(jwtService, PowerMockito.method(JwtService.class, "extractClaim", String.class, Function.class))
                .withArguments(token, Function.identity())
                .thenReturn(expectedUsername);

        String actualUsername = jwtService.extractUserName(token);

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testExtractUserName4() throws Exception {
        String token = "exampleToken";
        String expectedUsername = "testUser";

        // Мокируем приватный метод extractClaim
        PowerMockito.when(jwtService, "extractClaim", any(String.class), any(Function.class))
                .thenReturn(expectedUsername);

        String actualUsername = jwtService.extractUserName(token);

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testExtractUserName5() throws Exception {
        String token = "exampleToken";
        String expectedUsername = "testUser";

        // Мокируем приватный метод extractClaim
        PowerMockito.when(jwtService, PowerMockito.method(JwtService.class, "extractClaim", String.class, Function.class))
                .withArguments(token, Function.identity())
                .thenReturn(expectedUsername);

        String actualUsername = jwtService.extractUserName(token);

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testExtractUserName6() {
        jwtService = Mockito.mock(JwtService.class);

        String token = "exampleToken";
        Claims claims = Mockito.mock(Claims.class);
        Mockito.when(jwtService.extractAllClaims(token)).thenReturn(claims);
        Mockito.when(claims.getSubject()).thenReturn("testUser");

        String userName = jwtService.extractUserName(token);

        assertEquals("testUser", userName);
    }

    @Test
    void testExtractUserName7() {
        JwtService jwtService = new JwtService();
        String token = "your_jwt_token_here";

        Claims mockClaims = Mockito.mock(Claims.class);
        Mockito.when(mockClaims.getSubject()).thenReturn("testUser");

        JwtService spyJwtService = Mockito.spy(jwtService);
        Mockito.doReturn(mockClaims).when(spyJwtService).extractAllClaims(token);

        String userName = spyJwtService.extractUserName(token);

        assertEquals("testUser", userName);
    }

    @Test
    void testExtractUserName8() {
        Claims claimsMock = Mockito.mock(Claims.class);
        // Мокируем extractAllClaims для возвращения заглушки
        when(jwtService.extractAllClaims(any())).thenReturn(claimsMock);//Mockito.mock(Claims.class));

        // Тестируемый токен
        String token = "testToken";

        // Перехватываем вызов extractClaim и возвращаем фиктивное значение
        when(jwtService.extractClaim(any(), any())).thenAnswer(invocation -> {
            Function<Claims, String> function = invocation.getArgument(1);
            //Claims claims = Mockito.mock(Claims.class);
            Claims claims = invocation.getArgument(0);
            when(claims.getSubject()).thenReturn("testUser");
            return function.apply(claims);
        });

        //Вызываем метод, который мы тестируем
        String extractedUserName = jwtService.extractUserName(token);

        // Проверяем, что метод вернул ожидаемый результат
        assertEquals("testUser", extractedUserName);
    }

    @Test
    void testExtractUserName9() {
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
    void testExtractUserName10() {
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
    void testExtractUserName11() {
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
    void testExtractUserName12() {
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
    void testExtractUserName13() {
        JwtService jwtService = new JwtService();
        String token = "your_test_token_here";
        String extractedUserName = jwtService.extractUserName(token);
        assertNotNull(extractedUserName);
        // Добавьте дополнительные проверки при необходимости
    }

    @Test
    void testExtractUserName14() {
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

//     Для исправления ошибки WeakKeyException Вам нужно использовать ключ с размером не менее
//     256 бит для алгоритма HMAC-SHA. Вы можете создать такой ключ с помощью метода Jwts.hmacShaKeyFor()
//     и передать его в метод signWith() при создании JWT. Вот пример исправленного кода:


    @Test
    void testExtractUserName15() {
        // Создание тестового токена
        String token = Jwts.builder()
                .setSubject("testUser")
                .signWith((Key) Jwts.header()) // Замените "your-secure-key" на Ваш секретный ключ
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
    void testExtractUserName16() {
        MockitoAnnotations.initMocks(this); // инициализируем моки

        // Задаем поведение мока для метода extractClaim
        Mockito.when(jwtService.extractClaim(Mockito.anyString(), Mockito.any()))
                .thenAnswer(invocation -> {
                    // Получаем аргументы метода
                    String token = invocation.getArgument(0);
                    Function<Claims, String> claimsResolver = invocation.getArgument(1);

                    // Создаем фиктивные Claims (подделанный объект для теста)
                    Claims claims = Mockito.mock(Claims.class);
                    // Задаем поведение для метода getSubject() фиктивных Claims
                    Mockito.when(claims.getSubject()).thenReturn("testUser");

                    // Возвращаем результат вызова функции с фиктивными Claims
                    return claimsResolver.apply(claims);
                });

//         Вызываем тестируемый метод
        String extractedUserName = jwtService.extractUserName("dummyToken");

        // Проверяем, что метод extractUserName вернул ожидаемый результат
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

//    @Test
//    void testGenerateToken2() {
//        JwtService jwtService = new JwtService();
//
//        user.setLogin(request.getLogin());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(AllRoles.ROLE_USER);
//
//        UserDetails userDetails = User.withUsername("testUser")
//                .username("testUser")
//                .password("password")
//                .roles("USER")
//                .build();
//        String generatedToken = jwtService.generateToken(userDetails);
//        assertNotNull(generatedToken);
//    }

    @Test
    void testGenerateToken3() throws Exception {
        String expectedToken = "exampleToken";

        // Мокируем приватный метод generateToken
        PowerMockito.when(jwtService, "generateToken", any(Map.class), any(UserDetails.class))
                .thenReturn(expectedToken);

        String actualToken = jwtService.generateToken(user);

        assertEquals(expectedToken, actualToken);
    }

    @Test
    void testGenerateToken4() {
        // Устанавливаем значения для userDetailsMock
        when(userDetails.getUsername()).thenReturn("testUser");
        when(userDetails instanceof User).thenReturn(true);
        when(((User) userDetails).getUserId()).thenReturn(UUID.randomUUID());
        when(((User) userDetails).getLogin()).thenReturn("testUser");
        when(((User) userDetails).getRole()).thenReturn(AllRoles.ROLE_USER);

        String token = jwtService.generateToken(userDetails);

        assertTrue(token != null && !token.isEmpty());
    }
    @Test
    void testGenerateToken5() {
        String expectedToken = "generatedToken";
        when(jwtService.generateToken(userDetails)).thenReturn(expectedToken);

        String actualToken = jwtService.generateToken(userDetails);

        assertEquals(expectedToken, actualToken);
    }

//    @Test
//    void testIsTokenValid1() {
//        JwtService jwtService = new JwtService();
//        User userDetails = User.builder()
//                .username("testUser")
//                .password("password")
//                .roles("USER")
//                .build();
//        String token = jwtService.generateToken(userDetails);
//        assertTrue(jwtService.isTokenValid(token, userDetails));
//        // Добавьте дополнительные проверки при необходимости
//
//    }

    @Test
    void testIsTokenValid2() throws Exception {
        String token = "exampleToken";

        // Мокируем приватный метод extractUserName
        PowerMockito.when(jwtService, "extractUserName", any(String.class)).thenReturn(user.getLogin());

        // Мокируем приватный метод isTokenExpired
        PowerMockito.when(jwtService, "isTokenExpired", any(String.class)).thenReturn(false);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }
    @Test
    void testIsTokenValid3() {
        String token = "exampleToken";
        when(jwtService.extractUserName(token)).thenReturn(user.getLogin());
        when(jwtService.isTokenExpired(token)).thenReturn(false);

        boolean isValid = jwtService.isTokenValid(token, user);

        assertTrue(isValid);
    }

    @Test
    void testIsTokenValid4() {
        String token = "exampleToken";
        when(userDetails.getUsername()).thenReturn("user");
        when(jwtService.extractUserName(token)).thenReturn("user");
        when(jwtService.isTokenExpired(token)).thenReturn(false);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }
}
// Замените "your-secure-key" на Ваш секретный ключ длиной не менее 256 бит. Это позволит создать ключ,
// который будет безопасным для использования с алгоритмом HMAC-SHA.