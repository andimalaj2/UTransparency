package com.utransparency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utransparency.model.UserUniversity;

@Repository("userUniversityRepository")
public interface UserUniversityRepository extends JpaRepository<UserUniversity,Integer> {
	
	@Query(value="select * from user_university where user_id = ?1",nativeQuery = true)
	UserUniversity  findByUser(int userID);

}
