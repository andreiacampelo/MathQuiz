package com.andreia.mathquiz.controller;

import com.andreia.mathquiz.dto.ProfileRequest;
import com.andreia.mathquiz.model.Profile;
import com.andreia.mathquiz.service.MathQuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final MathQuizService service;

    public ProfileController(MathQuizService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profile create(@Valid @RequestBody ProfileRequest request) {
        return service.createProfile(request);
    }

    @GetMapping
    public List<Profile> list() { return service.listProfiles(); }
}
