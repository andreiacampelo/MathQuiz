package com.andreia.mathquiz.service;

import com.andreia.mathquiz.dto.*;
import com.andreia.mathquiz.model.*;
import com.andreia.mathquiz.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MathQuizService {
    private final ProfileRepository profileRepository;
    private final ProjectRepository projectRepository;
    private final TechnologyRepository technologyRepository;
    private final FeedbackRepository feedbackRepository;

    public MathQuizService(ProfileRepository profileRepository,
                           ProjectRepository projectRepository,
                           TechnologyRepository technologyRepository,
                           FeedbackRepository feedbackRepository) {
        this.profileRepository = profileRepository;
        this.projectRepository = projectRepository;
        this.technologyRepository = technologyRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public Profile createProfile(ProfileRequest request) {
        return profileRepository.save(new Profile(request.name(), request.email()));
    }

    public List<Profile> listProfiles() {
        return profileRepository.findAll();
    }

    public Technology createTechnology(TechnologyRequest request) {
        return technologyRepository.save(new Technology(request.name()));
    }

    public List<Technology> listTechnologies() {
        return technologyRepository.findAll();
    }

    public Project createProject(ProjectRequest request) {
        Profile profile = profileRepository.findById(request.profileId())
            .orElseThrow(() -> new IllegalArgumentException("Profile não encontrado"));

        Set<Technology> technologies = new HashSet<>();
        if (request.technologyIds() != null) {
            for (Long id : request.technologyIds()) {
                Technology technology = technologyRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Technology não encontrada: " + id));
                technologies.add(technology);
            }
        }

        Project project = new Project();
        project.setTitle(request.title());
        project.setQuestion(request.question());
        project.setCorrectAnswer(request.correctAnswer());
        project.setProfile(profile);
        project.setTechnologies(technologies);
        return projectRepository.save(project);
    }

    public List<Project> listProjects() {
        return projectRepository.findAll();
    }

    public Feedback createFeedback(FeedbackRequest request) {
        Project project = projectRepository.findById(request.projectId())
            .orElseThrow(() -> new IllegalArgumentException("Project não encontrado"));

        Feedback feedback = new Feedback();
        feedback.setProject(project);
        feedback.setStudentAnswer(request.studentAnswer());
        feedback.setCorrect(project.getCorrectAnswer().equalsIgnoreCase(request.studentAnswer().trim()));
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> listFeedbacks() {
        return feedbackRepository.findAll();
    }
}
