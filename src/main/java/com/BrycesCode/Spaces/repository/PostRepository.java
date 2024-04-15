package com.BrycesCode.Spaces.repository;

import com.BrycesCode.Spaces.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
