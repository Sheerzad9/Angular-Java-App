package com.myapp.app.model;

import com.myapp.app.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFeed {
    private Post post;
    private int userId;
    private String fullName;
    private String profilePic;
}
