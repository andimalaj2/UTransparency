package com.utransparency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utransparency.model.UserUniversity;
import com.utransparency.repository.UserUniversityRepository;

@Service("userUniversityService")
public class UserUniversityServiceImpl {
	
	@Autowired
	private UserUniversityRepository userUniversityRepository;
	
	public UserUniversity findByUser(int userID) {
		
		return userUniversityRepository.findByUser(userID);
		
	}

}
