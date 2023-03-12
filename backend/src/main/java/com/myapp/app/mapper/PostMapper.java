package com.myapp.app.mapper;

import com.myapp.app.dto.PostFeed;
import com.myapp.app.dto.User;
import com.myapp.app.entity.Post;
import com.myapp.app.jpa.CommentRepo;
import com.myapp.app.model.CommentsWUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {
    @Autowired
    private final CommentRepo commentRepo;
    @Autowired
    private final CommentMapper commentMapper;
    @Autowired
    private final UserMapper userMapper;

    public List<PostFeed> mapPostEntityToPostFeed(List<Post> postEntityList){
        List<PostFeed> postFeedListed = new ArrayList<>();
        postEntityList.forEach( post -> {
            List<CommentsWUser> commentsWUserList = this.commentRepo.findAllByPostId(post.getId()).stream().
                    map(e -> this.commentMapper.mapCommentEntityToCommentsWUser(e)).collect(Collectors.toList());

            User user = this.userMapper.mapUserEntityToUser(post.getUser());

            PostFeed tempPost = new PostFeed(post, commentsWUserList, user);
            postFeedListed.add(tempPost);
        });
        return postFeedListed;

    }
}
