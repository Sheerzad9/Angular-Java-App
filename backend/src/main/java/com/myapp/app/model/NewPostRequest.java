package com.myapp.app.model;

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
