package com.andreia.mathquiz.repository;

import com.andreia.mathquiz.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}
