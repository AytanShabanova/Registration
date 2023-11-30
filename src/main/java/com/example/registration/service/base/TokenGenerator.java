package com.example.registration.service.base;

public interface TokenGenerator <T> {

    String generate(T obj);

}
