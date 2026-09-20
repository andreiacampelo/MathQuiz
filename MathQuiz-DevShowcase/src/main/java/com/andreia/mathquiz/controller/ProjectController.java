package com.andreia.mathquiz.controller;

import com.andreia.mathquiz.dto.ProjectRequest;
import com.andreia.mathquiz.model.Project;
import com.andreia.mathquiz.service.MathQuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Project> list() { return service.listProjects(); }
}
