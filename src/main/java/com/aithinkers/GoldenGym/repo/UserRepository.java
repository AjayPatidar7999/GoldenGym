package com.aithinkers.GoldenGym.repo;

import com.aithinkers.GoldenGym.entity.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.authorities WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);


    Optional<User> findByMobile(String mobile);
    
    @Query("SELECT u FROM User u JOIN u.authorities a WHERE a.role = :role")
    List<User> findUsersByRole(@Param("role") String role);
    
}
