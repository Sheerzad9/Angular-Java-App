package com.myapp.app.service;

import com.myapp.app.dto.PostFeed;
import com.myapp.app.entity.Post;
import com.myapp.app.jpa.CommentRepo;
import com.myapp.app.jpa.PostRepo;
import com.myapp.app.mapper.CommentMapper;
import com.myapp.app.mapper.PostMapper;
import com.myapp.app.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostRepo postRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private final CommentMapper commentMapper;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final PostMapper postMapper;

    @Override
    public Post getPostWithId(int id) {
        return this.postRepo.findById(id);
    }

    @Override
    public List<Post> getPosts() {
        return this.postRepo.findAll();
    }

    @Override
    public List<PostFeed> getPostsASCOrder() {
          List<Post> postEntityList =  this.postRepo.findAll(Sort.by(Sort.Direction.ASC, "timestamp"));
        List<PostFeed> postFeedListed = this.postMapper.mapPostEntityToPostFeed(postEntityList);
        return postFeedListed;
    }

    @Override
    public List<PostFeed> getPostsDESCOrder() {
        List<Post> postEntityList = this.postRepo.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
        List<PostFeed> postFeedListed = this.postMapper.mapPostEntityToPostFeed(postEntityList);
        return postFeedListed;
    }

    @Override
    public Post addPost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return this.postRepo.save(post);
    }
}
