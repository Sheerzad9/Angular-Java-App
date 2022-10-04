package com.myapp.app.Service;

import com.myapp.app.model.AppUser;

import java.util.List;

public interface UserService {
   AppUser getUserWithEmail(String email);
   List<AppUser> getUsers();
   AppUser addUser(AppUser user);
}
