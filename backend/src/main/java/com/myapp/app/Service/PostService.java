package com.myapp.app.Service;

import com.myapp.app.model.AppUser;
import com.myapp.app.model.Post;

public interface PostService {
    AppUser getUser(String email);
    Post addPost(Post post);
}
