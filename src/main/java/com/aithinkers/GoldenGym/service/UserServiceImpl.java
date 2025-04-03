package com.aithinkers.GoldenGym.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection; @Lazy on password encoder only if needed
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findUsersByRole(role);
    }

    @Override
    public void verifyUser(User user) {
        user.setVerified(true);
        userRepository.save(user);
    }

    @Override
    public User findById(long theId) {
        return userRepository.findById(theId)
                .orElseThrow(() -> new RuntimeException("Did not find user id - " + theId));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long theId) {
        userRepository.deleteById(theId);
    }
}
