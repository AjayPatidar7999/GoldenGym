package com.aithinkers.GoldenGym.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aithinkers.GoldenGym.entity.Authority;
import com.aithinkers.GoldenGym.repo.AuthorityRepository;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }
}

