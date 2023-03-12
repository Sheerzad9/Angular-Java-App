package com.myapp.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "comment")
    private String comment;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String comment){
        this.comment = comment;
    }
}
