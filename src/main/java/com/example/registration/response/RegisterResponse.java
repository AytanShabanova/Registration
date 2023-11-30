package com.example.registration.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterResponse {
    String email;
}
