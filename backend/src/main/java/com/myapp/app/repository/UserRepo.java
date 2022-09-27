package com.myapp.app.repository;

import com.myapp.app.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Integer> {
    AppUser findByEmail(String email);
}
