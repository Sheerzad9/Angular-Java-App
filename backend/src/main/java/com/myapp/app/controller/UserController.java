package com.myapp.app.controller;

import com.myapp.app.Service.UserService;
import com.myapp.app.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

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
