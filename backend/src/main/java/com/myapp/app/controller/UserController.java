package com.myapp.app.controller;

import com.myapp.app.Service.UserService;
import com.myapp.app.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return this.userService.getUsers();
    }
}
