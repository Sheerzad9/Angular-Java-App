package com.myapp.app.service;

import com.myapp.app.dto.PostFeed;
import com.myapp.app.dto.SignUpRequest;
import com.myapp.app.dto.UserDto;
import com.myapp.app.dto.UserProfileResponse;
import com.myapp.app.entity.Post;
import com.myapp.app.entity.UserEntity;
import com.myapp.app.exception.BadCredentialsException;
import com.myapp.app.exception.UserNotFoundException;
import com.myapp.app.jpa.CommentRepo;
import com.myapp.app.jpa.PostRepo;
import com.myapp.app.jpa.UserRepo;
import com.myapp.app.mapper.CommentMapper;
import com.myapp.app.mapper.UserMapper;
import com.myapp.app.model.AuthenticateResponse;
import com.myapp.app.model.CommentsWUser;
import com.myapp.app.model.JwtRequest;
import com.myapp.app.model.JwtResponse;
import com.myapp.app.userdetails.MyUserDetailsService;
import com.myapp.app.utility.JWTUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final PostRepo postRepo;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final CommentRepo commentRepo;
    @Autowired
    private final CommentMapper commentMapper;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final MyUserDetailsService myUserDetailsService;
    @Autowired
    private final JWTUtility jwtUtility;

    @Override
    public UserEntity getUserWithEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public UserProfileResponse getUserWithIdAndPosts(int id) {
        //return this.userRepo.findById(id);
        UserEntity tempUser = this.userRepo.findById(id);
        if (tempUser == null){
            throw new UserNotFoundException("User with id: "+id+", was not found");
        }

        UserDto userDto = this.userMapper.mapUserEntityToUser(tempUser);

        List<Post> posts = this.postRepo.findByUserId(id);
        List<PostFeed> postFeeds = new ArrayList<>();

        posts.forEach(post -> {
            List<CommentsWUser> commentsWUserList = this.commentRepo.findAllByPostId(post.getId()).stream()
                    .map(e -> this.commentMapper.mapCommentEntityToCommentsWUser(e)).collect(Collectors.toList());
            postFeeds.add(new PostFeed(post, commentsWUserList, userDto));
        });


        UserProfileResponse userProfileResponse = new UserProfileResponse(userDto, postFeeds);

        return userProfileResponse;
    }

    @Override
    public UserDto addUser(SignUpRequest signUpRequest) {
        UserEntity tempUser = this.userMapper.mapSignUpRequestToUserEntity(signUpRequest);
        UserDto userDto = new UserDto();
        try {
            UserEntity userEntity = this.userRepo.save(tempUser);
            /*if (userEntity == null) {
                throw new BadCredentialsException("User with given email: " + signUpRequest.getEmail() + ", already exist");
            }*/
            userDto = this.userMapper.mapUserEntityToUser(userEntity);
        } catch (Exception e) {
            System.out.println("Exception ----");
            e.printStackTrace();
            throw new BadCredentialsException("User with given email: " + signUpRequest.getEmail() + ", already exist");
        }
        return userDto;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return this.userRepo.save(user);
    }

    @Override
    public UserEntity getUserWithId(int id) {
        UserEntity userEntity = this.userRepo.findById(id);
        //User user = this.userMapper.mapUserEntityToUser(userEntity);
        return userEntity;
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntityList = this.userRepo.findAll();
        List<UserDto> userDtoList = userEntityList.stream().map(e -> this.userMapper.mapUserEntityToUser(e)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void authenticateUser(JwtRequest jwtRequest) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid credentials, please try again");
        }
    }

    @Override
    public AuthenticateResponse generateAuthenticationResponse(JwtRequest jwtRequest) {
        // get user details
        final UserDetails userDetails
                = this.myUserDetailsService.loadUserByUsername(jwtRequest.getEmail());

        UserEntity tempUser = this.userRepo.findByEmail(jwtRequest.getEmail());
        UserDto userDto = this.userMapper.mapUserEntityToUser(tempUser);

        // generate jwttoken with user data
        final String token
                = this.jwtUtility.generateToken(userDetails);

        return new AuthenticateResponse(userDto,new JwtResponse(token, this.jwtUtility.getExpirationTimeInMillis(token)));
    }
}
