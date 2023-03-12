package com.myapp.app.model;

import com.myapp.app.dto.UserDto;
import com.myapp.app.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsWUser {
    private Comment comment;
    private UserDto userDto;
}
