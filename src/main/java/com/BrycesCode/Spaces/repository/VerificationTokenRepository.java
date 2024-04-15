package com.BrycesCode.Spaces.repository;

import com.BrycesCode.Spaces.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
