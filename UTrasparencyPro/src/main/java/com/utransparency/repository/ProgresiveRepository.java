package com.utransparency.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.utransparency.model.Progresivet;

@Repository("progresivetRepository")
public interface ProgresiveRepository extends JpaRepository<Progresivet, Integer>{
	
	@Query(value="SELECT tp.typeprogresive_id, tp.name, tp.sub_type,p.progresivet_id,p.refered_date, p.university_id, p.mount, "
			+ "p.type_mount FROM `progresivet` p right outer join type_progresive tp "
			+ "ON p.typeprogresive_id = tp.typeprogresive_id", nativeQuery = true)
	List<Progresivet> joinProgresive();
	
	@Query(value="SELECT * FROM `progresivet` where date_format(refered_date,\"%Y-%m\") = ?1 and university_id = ?2", nativeQuery = true )
	List<Progresivet> filterProgresive(String muaji, int universityId);
	
	@Query(value="SELECT * FROM `progresivet` p inner join `type_progresive` t on p.typeprogresive_id = t.typeprogresive_id  "
			+ "where date_format(refered_date,\"%Y-%m\") = ?1 "
			+ "and university_id = ?2 and t.sub_type=1", nativeQuery = true )
	List<Progresivet> filterProgresiveInc(String muaji, int universityId);
	
	@Query(value="SELECT * FROM `progresivet` p inner join `type_progresive` t on p.typeprogresive_id = t.typeprogresive_id  "
			+ "where date_format(refered_date,\"%Y-%m\") = ?1 "
			+ "and university_id = ?2 and t.sub_type=2", nativeQuery = true )
	List<Progresivet> filterProgresiveExp(String muaji, int universityId);
	
	@Query(value="SELECT * FROM `progresivet` p inner join `type_progresive` t on p.typeprogresive_id = t.typeprogresive_id  "
			+ "where date_format(refered_date,\"%Y-%m\") = ?1 "
			+ "and university_id = ?2 and t.sub_type=3", nativeQuery = true )
	List<Progresivet> filterProgresiveExpS(String muaji, int universityId);

}
