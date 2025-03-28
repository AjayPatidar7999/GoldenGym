package com.aithinkers.GoldenGym.repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.aithinkers.GoldenGym.entity.Authority;
import com.aithinkers.GoldenGym.entity.User;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByUser (User user);
}
