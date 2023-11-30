package com.example.registration.service.base;

public interface TokenReader <T> {

    T read(String token);

}
