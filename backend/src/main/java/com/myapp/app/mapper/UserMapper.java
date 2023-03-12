package com.myapp.app.mapper;

import com.myapp.app.dto.SignUpRequest;
import com.myapp.app.dto.UserDto;
import com.myapp.app.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapUserEntityToUser(UserEntity userEntity){
        return new UserDto(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getFirstName()+" "+userEntity.getLastName(), userEntity.getEmail(), userEntity.getProfilePicUrl());
    }

    public UserEntity mapSignUpRequestToUserEntity(SignUpRequest signUpRequest){
        UserEntity user = new UserEntity();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        return user;
    }
}
