package com.aithinkers.GoldenGym.repo;





import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aithinkers.GoldenGym.entity.OtpVerification;
import com.aithinkers.GoldenGym.entity.User;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {

	Optional<OtpVerification> findByUser(User user);
    // You can add custom query methods if needed
}

