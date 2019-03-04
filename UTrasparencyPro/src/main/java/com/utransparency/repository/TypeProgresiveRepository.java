package com.utransparency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utransparency.model.TypeProgresive;

@Repository("typeProgresiveRepository")
public interface TypeProgresiveRepository extends JpaRepository<TypeProgresive,Integer> {
	
	List<TypeProgresive> findAll();
	

}
