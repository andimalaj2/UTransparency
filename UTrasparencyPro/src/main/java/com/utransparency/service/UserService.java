package com.utransparency.service;

import com.utransparency.model.University;
import com.utransparency.model.User;
import com.utransparency.model.UserUniversity;

public interface UserService {
	  
	 public User findUserByEmail(String email);
	 
	 public void saveUser(User user);
	 
	 public void saveUserUniv(User user,University university);
	}