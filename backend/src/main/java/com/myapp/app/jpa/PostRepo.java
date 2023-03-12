package com.myapp.app.jpa;

import com.myapp.app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    Post findById(int id);

    @Query(nativeQuery = true, value =
            "SELECT * FROM localdb.posts WHERE user_id = :userId ORDER BY timestamp DESC")
    List<Post> findByUserId(@Param("userId") int userId);

}
