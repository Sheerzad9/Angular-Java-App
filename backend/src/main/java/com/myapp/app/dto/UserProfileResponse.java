package com.myapp.app.model;

import com.myapp.app.dto.PostFeed;
import com.myapp.app.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Setter
@Getter
public class UserProfileResponse {
    private UserEntity user;
    private List<PostFeed> posts;

    public void generatePostFeeds() {
        if (this.posts == null) this.posts = new ArrayList<>();
        this.user.getPosts().forEach(post -> {
            PostFeed tempPostFeed = new PostFeed(post, null, post.getUser().getId(), post.getUser().getFirstName()+" "+post.getUser().getLastName(), post.getUser().getProfilePicUrl());
            tempPostFeed.generateCommentsList();
            this.posts.add(tempPostFeed);
        });
    }
}
