package com.BrycesCode.Spaces.repository;

import com.BrycesCode.Spaces.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
