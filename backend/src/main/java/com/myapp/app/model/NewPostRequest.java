package com.myapp.app.model;

import com.myapp.app.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getters and Setters
@NoArgsConstructor
@AllArgsConstructor
public class NewPostRequest {
    private String userEmail;
    private Post post;
}
