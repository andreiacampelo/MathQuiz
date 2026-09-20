package com.andreia.mathquiz.controller;

import com.andreia.mathquiz.dto.TechnologyRequest;
import com.andreia.mathquiz.model.Technology;
import com.andreia.mathquiz.service.MathQuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {
    private final MathQuizService service;

    public TechnologyController(MathQuizService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Technology create(@Valid @RequestBody TechnologyRequest request) {
        return service.createTechnology(request);
    }

    @GetMapping
    public List<Technology> list() { return service.listTechnologies(); }
}
