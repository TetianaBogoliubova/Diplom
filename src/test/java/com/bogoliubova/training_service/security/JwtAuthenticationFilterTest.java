package com.bogoliubova.training_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService = mock(JwtService.class);
    @Mock
    private UserService userService = mock(UserService.class);
    private final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtService, userService);
    @Mock
    private FilterChain filterChain = mock(FilterChain.class);
    @Mock
    private HttpServletRequest request = mock(HttpServletRequest.class);
    @Mock
    private HttpServletResponse response = mock(HttpServletResponse.class);
    @Mock
    private UserDetails userDetails = mock(UserDetails.class);
    @Mock
    private UserDetailsService userDetailsService = mock(UserDetailsService.class);


    @Test
//на отсутствие заголовка Authorization в HTTP-запросе
    void doFilterInternalWithoutAuthorizationHeaderTest() throws Exception {

        Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
//если в заголовке Authorization присутствует токен без префикса "Bearer ".
    void doFilterInternalHeaderWithoutBearerPrefixTest() throws ServletException, IOException {

        Mockito.when(request.getHeader("Authorization")).thenReturn("Token token123");

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
//на корректность аутентификации с использованием действительного JWT токена.
    void doFilterInternalValidTokenAuthenticationTest() throws Exception {
        String validToken = "valid_token_here";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
        when(jwtService.extractUserName(validToken)).thenReturn("username");
        when(userService.userDetailsService()).thenReturn(userDetailsService);
        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
        when(jwtService.isTokenValid(validToken, userDetails)).thenReturn(true);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityContext.setAuthentication(authToken);
        SecurityContextHolder.setContext(securityContext);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
//на  передачу недействительного JWT токена
    void doFilterInternalInvalidTokenTest() throws Exception {

        when(jwtService.isTokenValid(Mockito.anyString(), Mockito.any(UserDetails.class))).thenReturn(false);
        when(userDetailsService.loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);
        when(userService.userDetailsService()).thenReturn(userDetailsService);
        when(jwtService.isTokenValid(Mockito.anyString(), Mockito.any(UserDetails.class))).thenReturn(false);
        when(userService.userDetailsService().loadUserByUsername(Mockito.anyString())).thenReturn(userDetails);
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");

        filter.doFilterInternal(request, response, filterChain);

        Mockito.verify(filterChain).doFilter(request, response);
    }
}
