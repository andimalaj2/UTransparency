package com.utransparency.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.utransparency.model.TypeProgresive;

@Repository("typeProgresiveRepository")
public interface TypeProgresiveRepository extends JpaRepository<TypeProgresive,Integer> {
	
	List<TypeProgresive> findAll();
	
	@Query(value="select * from type_progresive where typeprogresive_id = ?1", nativeQuery = true )
	TypeProgresive findById(int idType);
	
	@Query(value="select * from type_progresive where sub_type = ?1", nativeQuery = true )
	List<TypeProgresive> findBySubtype(int idType);

}
