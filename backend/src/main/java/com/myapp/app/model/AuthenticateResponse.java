package com.myapp.app.model;

import com.myapp.app.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticateResponse {
    private AppUser user;
    private JwtResponse jwtResponse;
}
