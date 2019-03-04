package com.utransparency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.utransparency.model.Role;
import com.utransparency.model.University;
import com.utransparency.model.User;
import com.utransparency.model.UserUniversity;
import com.utransparency.repository.RoleRespository;
import com.utransparency.repository.UserRepository;
import com.utransparency.repository.UserUniversityRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {
 
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 private RoleRespository roleRespository;
 
 @Autowired
 private UserUniversityRepository userUniversityRepository;
 
 @Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;

 @Override
 public User findUserByEmail(String email) {
  return userRepository.findByEmail(email);
 }

 @Override
 public void saveUser(User user) {
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  user.setActive(1);
  Role userRole = roleRespository.findByRole("ADMIN");
  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
  userRepository.save(user);
 }
 
 public void saveUserUniv(User user,University university) {
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  user.setActive(1);
  Role userRole = roleRespository.findByRole("ADMIN_UNIV");
  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
  //user.setId(user.getId());
  userRepository.save(user);
  UserUniversity userUniversity = new UserUniversity(user.getId(),university.getUniversityid());
  userUniversityRepository.save(userUniversity);
 }

}
