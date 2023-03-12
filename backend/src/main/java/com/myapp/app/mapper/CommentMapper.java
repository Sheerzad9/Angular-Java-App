package com.myapp.app.mapper;

import com.myapp.app.dto.User;
import com.myapp.app.entity.Comment;
import com.myapp.app.model.CommentsWUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    @Autowired
    UserMapper userMapper;

    public CommentsWUser mapCommentEntityToCommentsWUser(Comment comment){
        User user = this.userMapper.mapUserEntityToUser(comment.getUser());
        return new CommentsWUser(comment, user);
    }
}
