package com.aithinkers.GoldenGym.service;

import com.aithinkers.GoldenGym.entity.TrainerAssignment;
import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.repo.TrainerAssignmentRepository;
import com.aithinkers.GoldenGym.repo.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerAssignmentServiceImpl implements TrainerAssignmentService {

    @Autowired
    private TrainerAssignmentRepository assignmentRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public void assignTrainerToMember(Long memberId, Long trainerId) {
        User member = userRepo.findById(memberId).orElseThrow();
        User trainer = userRepo.findById(trainerId).orElseThrow();

        TrainerAssignment existing = assignmentRepo.findByMemberId(memberId);
        if (existing != null) {
            existing.setTrainer(trainer);
            assignmentRepo.save(existing);
        } else {
            TrainerAssignment newAssign = new TrainerAssignment();
            newAssign.setMember(member);
            newAssign.setTrainer(trainer);
            assignmentRepo.save(newAssign);
        }
    }

    @Override
    public List<TrainerAssignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }
    
    @Override
    public Optional<TrainerAssignment> findTrainerForMember(User member) {
        return assignmentRepo.findByMember(member);
    }
    
    

}
