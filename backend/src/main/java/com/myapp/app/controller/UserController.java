package com.myapp.app.controller;

import com.myapp.app.dto.SignUpRequest;
import com.myapp.app.dto.UserDto;
import com.myapp.app.dto.UserProfileResponse;
import com.myapp.app.model.AuthenticateResponse;
import com.myapp.app.model.JwtRequest;
import com.myapp.app.model.JwtResponse;
import com.myapp.app.service.UserService;
import com.myapp.app.userdetails.MyUserDetailsService;
import com.myapp.app.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // Auth
    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        // check that email & password are correct, userService will throw error if credentials are bad and code below that won't be executed
        this.userService.authenticateUser(jwtRequest);

        AuthenticateResponse authenticateResponse = this.userService.generateAuthenticationResponse(jwtRequest);

        return new ResponseEntity<>(authenticateResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity signNewUser(@RequestBody SignUpRequest signUpRequest) {
        UserDto userDto = this.userService.addUser(signUpRequest);

        final UserDetails userDetails
                = this.myUserDetailsService.loadUserByUsername(userDto.getEmail());

        final String token
                = this.jwtUtility.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticateResponse(userDto, new JwtResponse(token, this.jwtUtility.getExpirationTimeInMillis(token))));
    }


    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/user")
    public ResponseEntity getUser(@RequestParam("id") int id){
        UserProfileResponse userProfileResponse = this.userService.getUserWithIdAndPosts(id);
        return ResponseEntity.ok(userProfileResponse);
    }
}
