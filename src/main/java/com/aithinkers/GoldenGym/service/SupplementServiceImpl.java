package com.aithinkers.GoldenGym.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aithinkers.GoldenGym.entity.Supplement;
import com.aithinkers.GoldenGym.repo.SupplementRepository;



@Service
public class SupplementServiceImpl implements SupplementService{

	
	  @Autowired
	    private SupplementRepository supplementRepository;

	    public List<Supplement> getAllSupplements() {
	        return supplementRepository.findAll();
	    }

	    public List<Supplement> getSupplementsByCategory(String category) {
	        return supplementRepository.findByCategory(category);
	    }

	    public Supplement getSupplementById(Long id) {
	        return supplementRepository.findById(id).orElse(null);
	    }
	
}

