package com.BrycesCode.Spaces.repository;

import com.BrycesCode.Spaces.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpacesRepository extends JpaRepository<Space, Long> {
}
