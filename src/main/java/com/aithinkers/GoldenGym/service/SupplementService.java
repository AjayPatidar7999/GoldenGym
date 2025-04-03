package com.aithinkers.GoldenGym.service;


import java.util.List;

import com.aithinkers.GoldenGym.entity.Supplement;



public interface SupplementService {

	
	List<Supplement> getAllSupplements();
	 List<Supplement> getSupplementsByCategory(String category);
	 Supplement getSupplementById(Long id);
}