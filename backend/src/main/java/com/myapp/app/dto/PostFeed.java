package com.myapp.app.dto;

import com.myapp.app.entity.Post;
import com.myapp.app.model.CommentsWUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFeed implements Serializable {
    private Post post;
    private List<CommentsWUser> postComments;
    private UserDto creator;
}
