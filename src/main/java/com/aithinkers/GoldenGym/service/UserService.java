package com.aithinkers.GoldenGym.service;




import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Use PasswordEncoder instead of a concrete implementation

    // Constructor injection; @Lazy on repository only if needed (remove if not required)
    public UserService( UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }
    
    public List<User> getUsersByRole(String role) {
        return userRepository.findUsersByRole(role);
    }

    public void verifyUser(User user) {
        user.setVerified(true);
        userRepository.save(user);
    }
}
