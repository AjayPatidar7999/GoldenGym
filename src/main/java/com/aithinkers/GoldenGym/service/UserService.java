package com.aithinkers.GoldenGym.service;

import com.aithinkers.GoldenGym.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);
    
    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    List<User> getAll();

    List<User> getUsersByRole(String role);

    void verifyUser(User user);

    User findById(long theId);

    void save(User user);

    void deleteById(Long theId);
}
