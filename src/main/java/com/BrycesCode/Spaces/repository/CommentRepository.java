package com.BrycesCode.Spaces.repository;

import com.BrycesCode.Spaces.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
