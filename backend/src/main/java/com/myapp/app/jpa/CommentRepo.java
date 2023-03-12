package com.myapp.app.jpa;

import com.myapp.app.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    @Query(nativeQuery = true, value =
    "SELECT * FROM localdb.comments where post_id = :postId")
    List<Comment> findAllByPostId(@Param("postId") int postId);
}
