package com.example.registration.controller;

import com.example.registration.dto.LoginPayLoad;

import com.example.registration.entity.User;
import com.example.registration.response.LoginResponse;
import com.example.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
   private final UserService userService;
@PostMapping("/save")
    public String saveEmployee(@RequestBody User userDto){

    String id= userService.save(userDto);
    return id;}


@PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginPayLoad loginDto) {
    LoginResponse loginResponse = userService.loginEmployee(loginDto);
    return ResponseEntity.ok(loginResponse);

}}



