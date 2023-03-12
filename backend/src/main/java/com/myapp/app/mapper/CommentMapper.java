package com.myapp.app.mapper;

import com.myapp.app.dto.UserDto;
import com.myapp.app.entity.Comment;
import com.myapp.app.model.CommentsWUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    @Autowired
    UserMapper userMapper;

    public CommentsWUser mapCommentEntityToCommentsWUser(Comment comment){
        UserDto userDto = this.userMapper.mapUserEntityToUser(comment.getUser());
        return new CommentsWUser(comment, userDto);
    }
}
