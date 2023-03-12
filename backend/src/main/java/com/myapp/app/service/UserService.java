package com.myapp.app.service;

import com.myapp.app.dto.SignUpRequest;
import com.myapp.app.dto.UserDto;
import com.myapp.app.dto.UserProfileResponse;
import com.myapp.app.entity.UserEntity;
import com.myapp.app.model.AuthenticateResponse;
import com.myapp.app.model.JwtRequest;

import java.util.List;

public interface UserService {
   UserEntity getUserWithEmail(String email);
   UserProfileResponse getUserWithIdAndPosts(int id);
   UserEntity getUserWithId(int id);
   List<UserDto> getUsers();
   UserDto addUser(SignUpRequest user);
   UserEntity updateUser(UserEntity user);
   void authenticateUser(JwtRequest jwtRequest);
   AuthenticateResponse generateAuthenticationResponse(JwtRequest jwtRequest);
}
