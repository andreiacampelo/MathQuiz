package com.andreia.mathquiz.controller;

import com.andreia.mathquiz.dto.FeedbackRequest;
import com.andreia.mathquiz.model.Feedback;
import com.andreia.mathquiz.service.MathQuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final MathQuizService service;

    public FeedbackController(MathQuizService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Feedback create(@Valid @RequestBody FeedbackRequest request) {
        return service.createFeedback(request);
    }

    @GetMapping
    public List<Feedback> list() { return service.listFeedbacks(); }
}
