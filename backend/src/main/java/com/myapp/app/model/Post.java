package com.myapp.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "timestamp")
    private Date timestamp;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    public Post(String title, String content, String imageUrl, Date timestamp){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
    }
}
