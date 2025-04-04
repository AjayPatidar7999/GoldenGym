package com.aithinkers.GoldenGym.controller;


import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.repo.UserRepository;
import com.aithinkers.GoldenGym.service.TrainerAssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TrainerAssignmentService assignmentService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<User> allUsers = userRepo.findAll();

        List<User> trainers = allUsers.stream()
                .filter(user -> user.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equalsIgnoreCase("TRAINER")))
                .collect(Collectors.toList());

        List<User> members = allUsers.stream()
                .filter(user -> user.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equalsIgnoreCase("MEMBER")))
                .collect(Collectors.toList());

        model.addAttribute("trainers", trainers);
        model.addAttribute("users", members);
        model.addAttribute("assignments", assignmentService.getAllAssignments());

        return "admin-dashboard";
    }

    @PostMapping("/assign-trainer")
    public String assignTrainer(@RequestParam Long memberId, @RequestParam Long trainerId) {
        assignmentService.assignTrainerToMember(memberId, trainerId);
        return "redirect:/admin";
    }
}

