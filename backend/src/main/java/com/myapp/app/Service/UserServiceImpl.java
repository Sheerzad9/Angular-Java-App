package com.myapp.app.Service;

import com.myapp.app.model.AppUser;
import com.myapp.app.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public AppUser addUser(AppUser user) {
        try {
            return this.userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public List<AppUser> getUsers() {
        return this.userRepo.findAll();
    }
}
