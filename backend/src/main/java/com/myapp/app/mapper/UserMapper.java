package com.myapp.app.mapper;

import com.myapp.app.dto.User;
import com.myapp.app.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserEntityToUser(UserEntity userEntity){
        return new User(userEntity.getId(), userEntity.getFirstName()+" "+userEntity.getLastName(), userEntity.getEmail(), userEntity.getProfilePicUrl());
    }
}
