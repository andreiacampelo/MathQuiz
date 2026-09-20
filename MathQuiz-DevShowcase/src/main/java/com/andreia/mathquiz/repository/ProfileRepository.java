package com.andreia.mathquiz.repository;

import com.andreia.mathquiz.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {}
