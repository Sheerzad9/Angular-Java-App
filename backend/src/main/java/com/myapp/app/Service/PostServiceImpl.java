package com.myapp.app.Service;

import com.myapp.app.model.AppUser;
import com.myapp.app.model.Post;
import com.myapp.app.repository.PostRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepo postRepo;

    @Autowired
    private UserService userService;

    @Override
    public AppUser getUser(String email) {
        return this.userService.getUserWithEmail(email);
    }

    @Override
    public Post addPost(Post post) {
        return postRepo.save(post);
    }
}
