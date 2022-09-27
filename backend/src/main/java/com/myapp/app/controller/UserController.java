package com.myapp.app.controller;

import com.myapp.app.Service.UserService;
import com.myapp.app.model.AppUser;
import com.myapp.app.model.JwtRequest;
import com.myapp.app.model.JwtResponse;
import com.myapp.app.userdetails.MyUserDetailsService;
import com.myapp.app.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
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
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        // check that email & password are correct
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
            //return  new ResponseEntity<>("INVALID_CREDENTIALS", HttpStatus.BAD_REQUEST);
        }

        // get user
        final UserDetails userDetails
                = this.myUserDetailsService.loadUserByUsername(jwtRequest.getEmail());

        // generate jwttoken with user data
        final String token
                = this.jwtUtility.generateToken(userDetails);

        //JwtResponse jwtResponse = new JwtResponse(token);

        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    @CrossOrigin()
    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping("/user")
    public AppUser addUser(@RequestBody AppUser user) {
        AppUser tempUser = this.userService.addUser(user);
        if (tempUser == null) {
            throw  new RuntimeException("User not found");
        }
        return tempUser;
    }
}
