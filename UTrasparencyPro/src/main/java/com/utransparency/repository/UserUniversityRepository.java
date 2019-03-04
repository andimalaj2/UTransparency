package com.utransparency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utransparency.model.UserUniversity;

@Repository("userUniversityRepository")
public interface UserUniversityRepository extends JpaRepository<UserUniversity,Integer> {
	
	

}
