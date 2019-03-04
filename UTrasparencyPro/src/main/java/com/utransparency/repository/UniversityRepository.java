package com.utransparency.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.utransparency.model.University;

@Repository("universityRepository")
public interface UniversityRepository  extends JpaRepository<University, Integer>{

	//University findByUniversity(String university);
	List<University> findAll();
}
