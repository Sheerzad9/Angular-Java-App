package com.myapp.app.service;

import com.myapp.app.entity.AppUser;
import com.myapp.app.entity.Post;
import com.myapp.app.repository.PostRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepo postRepo;

    @Autowired
    private UserService userService;

    @Override
    public List<Post> getPosts() {
        return this.postRepo.findAll();
    }

    @Override
    public List<Post> getPostsASCOrder() {
        return this.postRepo.findAll(Sort.by(Sort.Direction.ASC, "timestamp"));
    }

    @Override
    public List<Post> getPostsDESCOrder() {
        return this.postRepo.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }

    @Override
    public Post addPost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public AppUser getUser(String email) {
        return this.userService.getUserWithEmail(email);
    }
}
