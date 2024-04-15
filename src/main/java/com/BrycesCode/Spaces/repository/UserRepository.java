package com.BrycesCode.Spaces.repository;

import com.BrycesCode.Spaces.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
