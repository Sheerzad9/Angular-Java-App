package com.myapp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class AppUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name = "profile_pic_url")
    private String profilePicUrl;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    public void addPost(Post post){
        post.setUser(this);
        this.posts.add(post);
    }
}
