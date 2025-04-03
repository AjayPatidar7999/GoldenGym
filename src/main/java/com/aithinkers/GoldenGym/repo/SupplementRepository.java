package com.aithinkers.GoldenGym.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aithinkers.GoldenGym.entity.Supplement;


@Repository
public interface SupplementRepository extends JpaRepository<Supplement, Long> {
    List<Supplement> findByCategory(String category);
}