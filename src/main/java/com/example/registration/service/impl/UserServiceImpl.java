package com.example.registration.service.impl;

import com.example.registration.dto.LoginPayLoad;
import com.example.registration.entity.User;
import com.example.registration.repo.UserRepo;
import com.example.registration.response.LoginResponse;
import com.example.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String save(User userDto) {
        User user =new User(userDto.getId(), userDto.getName(),
                userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),userDto.getRole());
        userRepo.save(user);
        return user.getName();
    }

    @Override
    public LoginResponse loginEmployee(LoginPayLoad loginPayLoad) {

     User user1 = userRepo.findUserByEmail(loginPayLoad.getEmail());
     if (user1 !=null){
     String password= loginPayLoad.getPassword();
     String encodedPassword= user1.getPassword();
     Boolean isPswrdRight=passwordEncoder.matches(password,encodedPassword);
     if (isPswrdRight){
         Optional<User> employee= userRepo.findEmployeeByEmailAndPassword(loginPayLoad.getEmail(), loginPayLoad.getPassword());
         if (employee.isPresent()){
             return new LoginResponse("Login success","log suc");

         }else return new LoginResponse("Login failed","false");
     }else {
         return new LoginResponse("password Not Match","false");

     }

     }else {
         return new LoginResponse("email not excist","false");
     }
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
