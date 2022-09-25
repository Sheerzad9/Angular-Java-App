package com.myapp.app.Service;

import com.myapp.app.model.AppUser;
import com.myapp.app.repository.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public List<AppUser> getUsers() {
        return this.userRepo.findAll();
    }
}
