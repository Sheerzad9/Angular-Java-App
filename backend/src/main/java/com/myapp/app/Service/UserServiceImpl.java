package com.myapp.app.Service;

import com.myapp.app.model.AppUser;
import com.myapp.app.repository.PostRepo;
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
    PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public AppUser getUserWithEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public AppUser addUser(AppUser user) {
        try {
            return this.userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public AppUser updateUser(AppUser user) {
        return this.userRepo.save(user);
    }

    @Override
    public List<AppUser> getUsers() {
        return this.userRepo.findAll();
    }
}
