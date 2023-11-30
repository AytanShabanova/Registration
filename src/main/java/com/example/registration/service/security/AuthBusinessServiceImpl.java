package com.example.registration.service.security;


import com.example.registration.dto.LoginPayLoad;
import com.example.registration.dto.RegisterPayload;

import com.example.registration.entity.User;
import com.example.registration.response.LoginResponse;
import com.example.registration.response.RegisterResponse;
import com.example.registration.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessServiceImpl implements AuthBusinessService {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenManager accessTokenManager;
    private final UserService userService;

    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public LoginResponse login(LoginPayLoad payload) {
        LoginResponse loginResponse = prepareLoginResponse(payload.getEmail());
        authenticate(payload);

        return loginResponse;
    }


    @Override
    public void setAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities(), userDetails.getAuthorities())
        );
    }

    @Override
    public RegisterResponse register(RegisterPayload registerPayload) {

        return convertRegisterResponse(registerPayload);
    }

    // private util methods

    private void authenticate(LoginPayLoad request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

        } catch (AuthenticationException e) {
            throw new  RuntimeException("authentication error");
        }
    }

    private LoginResponse prepareLoginResponse(String email) {
        User user = userService.findByEmail(email);
        return LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .email(user.getEmail()).accessToken(user.getRole())
                .build();
    }

    private RegisterResponse convertRegisterResponse(RegisterPayload registerPayload) {
        User user = objectMapper.convertValue(registerPayload, User.class);
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_USER");

        String user1 = userService.save(user);
        return objectMapper.convertValue(user1, RegisterResponse.class);
    }
}
