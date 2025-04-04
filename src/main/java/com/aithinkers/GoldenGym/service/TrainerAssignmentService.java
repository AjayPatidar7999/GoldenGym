package com.aithinkers.GoldenGym.service;

import com.aithinkers.GoldenGym.entity.TrainerAssignment;
import com.aithinkers.GoldenGym.entity.User;

import java.util.List;
import java.util.Optional;

public interface TrainerAssignmentService {
    void assignTrainerToMember(Long memberId, Long trainerId);
    List<TrainerAssignment> getAllAssignments();
    Optional<TrainerAssignment> findTrainerForMember(User member);

    
}
