package com.myapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Setters and Getters
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    private String email;
    private String password;
}
