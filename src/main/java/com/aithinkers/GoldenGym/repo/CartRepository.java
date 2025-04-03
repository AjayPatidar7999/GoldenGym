package com.aithinkers.GoldenGym.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aithinkers.GoldenGym.entity.CartItem;



@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

	 CartItem findBySupplementId(Long supplementId);
	
}
