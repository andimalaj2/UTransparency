package com.utransparency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utransparency.model.University;
import com.utransparency.repository.UniversityRepository;

@Service("universityService")
public class UniversityServiceImpl implements UniversityService {
	
	@Autowired
	 private UniversityRepository universityRepository;

	@Override
	public List<University> findAll() {
		// TODO Auto-generated method stub
		return universityRepository.findAll();
	}

}
