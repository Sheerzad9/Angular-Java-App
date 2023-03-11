package com.myapp.app.repository;

import com.myapp.app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
    Post findById(int id);


}
