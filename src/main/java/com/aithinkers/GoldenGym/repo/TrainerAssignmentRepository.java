package com.aithinkers.GoldenGym.repo;

import com.aithinkers.GoldenGym.entity.TrainerAssignment;
import com.aithinkers.GoldenGym.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerAssignmentRepository extends JpaRepository<TrainerAssignment, Long> {
    TrainerAssignment findByMemberId(Long memberId);
 // Find trainer assigned to the currently logged-in member
    Optional<TrainerAssignment> findByMember(User member);

}
