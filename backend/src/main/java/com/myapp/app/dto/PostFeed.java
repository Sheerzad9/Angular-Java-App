package com.myapp.app.model;

import com.myapp.app.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFeed {
    private Post post;
    private List<CommentsWUser> postComments;
    private int userId;
    private String fullName;
    private String profilePic;

    public void generateCommentsList(){
        if(this.postComments == null) this.postComments = new ArrayList<>();
        this.post.getComments().forEach( comment -> {
            this.postComments.add(new CommentsWUser(comment, comment.getUser().getFirstName(), comment.getUser().getLastName(), comment.getUser().getProfilePicUrl(), comment.getUser().getId()));
        });
    }
}
