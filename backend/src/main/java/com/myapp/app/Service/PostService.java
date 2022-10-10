package com.myapp.app.Service;

import com.myapp.app.model.AppUser;
import com.myapp.app.model.Post;

import java.util.List;

public interface PostService {
    AppUser getUser(String email);
    Post addPost(Post post);
    List<Post> getPosts();
    List<Post> getPostsASCOrder();
    List<Post> getPostsDESCOrder();
}
