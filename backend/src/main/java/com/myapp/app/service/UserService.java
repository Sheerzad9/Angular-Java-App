package com.myapp.app.service;

import com.myapp.app.entity.AppUser;

import java.util.List;

public interface UserService {
   AppUser getUserWithEmail(String email);
   List<AppUser> getUsers();
   AppUser addUser(AppUser user);
   AppUser updateUser(AppUser user);
}
