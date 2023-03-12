package com.myapp.app.service;

import com.myapp.app.dto.PostFeed;
import com.myapp.app.entity.Post;

import java.util.List;

public interface PostService {
    Post getPostWithId(int id);
    Post updatePost(Post post);
    Post addPost(Post post);
    List<Post> getPosts();
    List<PostFeed> getPostsASCOrder();
    List<PostFeed> getPostsDESCOrder();
}
