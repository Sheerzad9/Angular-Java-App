package com.myapp.app.controller;

import com.myapp.app.Service.PostService;
import com.myapp.app.Service.UserService;
import com.myapp.app.model.AppUser;
import com.myapp.app.model.NewPostRequest;
import com.myapp.app.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @PostMapping("/post")
    public ResponseEntity addNewPost(@RequestBody NewPostRequest newPostRequest) {
        AppUser tempUser = postService.getUser(newPostRequest.getUserEmail());
        if (tempUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found with given email");
        }

        Post newPost = new Post(newPostRequest.getPost().getTitle(), newPostRequest.getPost().getContent(),
                newPostRequest.getPost().getImageUrl());
        tempUser.addPost(newPost);
        this.userService.updateUser(tempUser);
        return  ResponseEntity.status(HttpStatus.CREATED).body(tempUser);
    }
}
