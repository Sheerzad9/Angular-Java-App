package com.myapp.app.service;

import com.myapp.app.entity.AppUser;
import com.myapp.app.entity.Post;

import java.util.List;

public interface PostService {
    AppUser getUser(String email);
    Post addPost(Post post);
    List<Post> getPosts();
    List<Post> getPostsASCOrder();
    List<Post> getPostsDESCOrder();
}
