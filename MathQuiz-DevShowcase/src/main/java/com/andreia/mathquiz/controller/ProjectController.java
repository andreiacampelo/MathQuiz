package com.andreia.mathquiz.controller;

import com.andreia.mathquiz.dto.FeedbackRequest;
import com.andreia.mathquiz.dto.ProjectRequest;
import com.andreia.mathquiz.model.Project;
import com.andreia.mathquiz.service.MathQuizService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final MathQuizService service;

    public ProjectController(MathQuizService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@Valid @RequestBody ProjectRequest request) {
        return service.createProject(request);
    }

    @GetMapping
    public Page<Project> list(
            @RequestParam(required = false) String tecnologia,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.listProjects(tecnologia, PageRequest.of(page, size));
    }

        @PostMapping("/{id}/feedback")
    public com.andreia.mathquiz.model.Feedback addFeedback(@PathVariable Long id, @Valid @RequestBody FeedbackRequest request) {
        return service.addFeedback(id, request);
    }

    @PutMapping("/{id}/upvote")
    public Project upvote(@PathVariable Long id){
        return service.upvote(id);
    }
    
    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id){
        return service.getProjectById(id);
    }
}