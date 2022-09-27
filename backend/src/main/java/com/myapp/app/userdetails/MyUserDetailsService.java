package com.myapp.app.userdetails;

import com.myapp.app.model.AppUser;
import com.myapp.app.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = this.userRepo.findByEmail(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("User with email: "+email+", not found in the system");
        }
        return new UserDetailsImpl(appUser);
    }
}
