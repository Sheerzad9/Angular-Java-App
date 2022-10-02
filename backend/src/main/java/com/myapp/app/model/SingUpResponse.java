package com.myapp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SingUpResponse {
    private AppUser user;
    private JwtResponse jwtResponse;

    public SingUpResponse(AppUser user, JwtResponse jwtResponse) {
        this.user = user;
        this.jwtResponse = jwtResponse;
    }
}
