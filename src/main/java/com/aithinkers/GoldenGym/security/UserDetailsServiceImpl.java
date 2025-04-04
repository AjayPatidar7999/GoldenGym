package com.aithinkers.GoldenGym.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aithinkers.GoldenGym.entity.Authority;
import com.aithinkers.GoldenGym.entity.User;
import com.aithinkers.GoldenGym.repo.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Trying to authenticate: " + username);

        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            System.out.println("User  not found with email: " + username);
            throw new UsernameNotFoundException("User  not found with email: " + username);
        }

        User user = userOptional.get();
        
        // Check if the user is verified
        if (!user.isVerified()) {
            System.out.println("User  is not verified: " + username);
            throw new UsernameNotFoundException("User  is not verified: " + username);
        }

        System.out.println("User  found: " + user.getEmail() + ", Roles: " + user.getAuthorities().stream()
            .map(Authority::getRole)
            .collect(Collectors.toList()));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getAuthorities().stream()
                        .map(authority -> "ROLE_" + authority.getRole()) // Ensure roles have "ROLE_"
                        .toArray(String[]::new))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
    
    
}