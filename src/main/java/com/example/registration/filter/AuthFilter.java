package com.example.registration.filter;

import com.example.registration.service.security.AccessTokenManager;
import com.example.registration.service.security.AuthBusinessServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final AccessTokenManager accessTokenManager;
    private final AuthBusinessServiceImpl authBusinessService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull(token) && token.startsWith("Bearer")){
            String decodeToken = token.substring(7);
            if (null !=accessTokenManager.getEmail(decodeToken)){
            authBusinessService.setAuthentication(accessTokenManager.getEmail(
                    decodeToken
            ));
            }
        }
        filterChain.doFilter(request,response);
    }
}
