package com.andreia.mathquiz.service;

import com.andreia.mathquiz.dto.*;
import com.andreia.mathquiz.model.*;
import com.andreia.mathquiz.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class MathQuizService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;
    private final FeedbackRepository feedbackRepository;

    public MathQuizService(ProjectRepository projectRepository, ProfileRepository profileRepository, TechnologyRepository technologyRepository, FeedbackRepository feedbackRepository) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
        this.feedbackRepository = feedbackRepository;
    }

    // --- PROJECT ---
    public Project createProject(ProjectRequest req){
        Project p = new Project();
        p.setTitle(req.title());
        p.setQuestion(req.question());
        p.setCorrectAnswer(req.correctAnswer());
        
        if(req.profileId() != null){
            Profile prof = profileRepository.findById(req.profileId())
                .orElseThrow(() -> new EntityNotFoundException("Profile não encontrado"));
            p.setProfile(prof);
        }
        if(req.technologyIds() != null && !req.technologyIds().isEmpty()){
            p.setTechnologies(new HashSet<>(technologyRepository.findAllById(req.technologyIds())));
        }
        return projectRepository.save(p);
    }

    public Page<Project> listProjects(String tecnologia, Pageable pageable){
        if(tecnologia != null && !tecnologia.isBlank()){
            return projectRepository.findByTechnologies_NameContainingIgnoreCase(tecnologia, pageable);
        }
        return projectRepository.findAll(pageable);
    }

    public Project getProjectById(Long id){
        return projectRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Projeto " + id + " não encontrado"));
    }

    public Project upvote(Long id){
        Project p = getProjectById(id);
        p.setUpvotes(p.getUpvotes() + 1);
        return projectRepository.save(p);
    }

    // --- FEEDBACK - LÓGICA DO QUIZ ---
    public Feedback addFeedback(Long projectId, FeedbackRequest req){
        Project p = getProjectById(projectId);
        Feedback f = new Feedback();
        f.setStudentAnswer(req.studentAnswer());
        f.setProject(p);
        // Verifica se a resposta está correta
        boolean isCorrect = p.getCorrectAnswer().trim().equalsIgnoreCase(req.studentAnswer().trim());
        f.setCorrect(isCorrect);
        
        p.getFeedbacks().add(f);
        return feedbackRepository.save(f);
    }

    public Feedback createFeedback(FeedbackRequest req){
        Project p = getProjectById(req.projectId());
        Feedback f = new Feedback();
        f.setStudentAnswer(req.studentAnswer());
        f.setProject(p);
        boolean isCorrect = p.getCorrectAnswer().trim().equalsIgnoreCase(req.studentAnswer().trim());
        f.setCorrect(isCorrect);
        return feedbackRepository.save(f);
    }

    public java.util.List<Feedback> listFeedbacks(){ return feedbackRepository.findAll(); }

    // --- PROFILE ---
    public Profile createProfile(ProfileRequest req){
        Profile prof = new Profile();
        prof.setName(req.name());
        prof.setEmail(req.email());
        return profileRepository.save(prof);
    }
    public java.util.List<Profile> listProfiles(){ return profileRepository.findAll(); }

    // --- TECHNOLOGY ---
    public Technology createTechnology(TechnologyRequest req){
        Technology t = new Technology();
        t.setName(req.name());
        return technologyRepository.save(t);
    }
    public java.util.List<Technology> listTechnologies(){ return technologyRepository.findAll(); }
}