package com.myapp.app.controller;

import com.myapp.app.service.PostService;
import com.myapp.app.service.UserService;
import com.myapp.app.entity.AppUser;
import com.myapp.app.model.NewPostRequest;
import com.myapp.app.entity.Post;
import com.myapp.app.model.PostFeed;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.file.Paths.get;

@RestController
public class PostController {
    public static final String DIRECTORY = System.getProperty("user.home")+"\\OneDrive\\Työpöytä\\Angular-Java-App\\angularfront\\src\\assets\\img\\posts\\";

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public ResponseEntity getPosts(@RequestParam("ascending") boolean ascending){
        List<Post> posts = ascending ? this.postService.getPostsASCOrder() : this.postService.getPostsDESCOrder();
        List<PostFeed> tempPostFeed = new ArrayList<>();
        posts.forEach( post -> {
            PostFeed tempPost = new PostFeed(post, post.getUser().getId(), post.getUser().getFirstName()+" "+post.getUser().getLastName(), post.getUser().getProfilePicUrl());
            tempPostFeed.add(tempPost);
        });
        return ResponseEntity.status(HttpStatus.OK).body(tempPostFeed);
    }

    @PostMapping("/post")
    public ResponseEntity addNewPost(@RequestBody NewPostRequest newPostRequest) {
        AppUser tempUser = postService.getUser(newPostRequest.getUserEmail());
        if (tempUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found with given email");
        }

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime() + (1000 * 60 * 60 * 3)); // To get EEST, todo: create better way to do this

        Post newPost = new Post(newPostRequest.getPost().getTitle(), newPostRequest.getPost().getContent(),
                newPostRequest.getPost().getImageUrl(), timestamp);
        tempUser.addPost(newPost);
        this.userService.updateUser(tempUser);
        return  ResponseEntity.status(HttpStatus.CREATED).body(tempUser);
    }


    @PostMapping("/file/upload")
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile multipartFile, @RequestParam("email") String email, @RequestParam("title") String title, @RequestParam("content") String content) throws IOException {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();

        // Resizing the image and saving the file to given destination
        Thumbnails.of(multipartFile.getInputStream()).size(600, 300).toFile(DIRECTORY+filename);
        //copy(multipartFile.getInputStream(), fileStorage, REPLACE_EXISTING);

        AppUser tempUser = this.userService.getUserWithEmail(email);

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime() ); // To get EEST, todo: create better way to do this

        Post newPost = new Post(title, content,
                "assets/img/posts/"+filename, timestamp);
        tempUser.addPost(newPost);
        this.userService.updateUser(tempUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully uploaded file to: "+fileStorage+", and updated post to db.");
    }
}
