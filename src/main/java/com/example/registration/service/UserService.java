package com.example.registration.service;


import com.example.registration.dto.LoginPayLoad;
import com.example.registration.entity.User;
import com.example.registration.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String save(User user);

    LoginResponse loginEmployee(LoginPayLoad loginPayLoad);
    User findByEmail(String email);
}
