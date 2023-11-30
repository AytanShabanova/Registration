package com.example.registration.service.security;



import com.example.registration.dto.LoginPayLoad;
import com.example.registration.dto.RegisterPayload;
import com.example.registration.response.LoginResponse;
import com.example.registration.response.RegisterResponse;

public interface AuthBusinessService {

    LoginResponse login(LoginPayLoad payload);



    void setAuthentication(String email);
    RegisterResponse register(RegisterPayload registerPayload);


}
