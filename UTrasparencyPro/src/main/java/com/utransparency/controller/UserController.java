package com.utransparency.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utransparency.model.University;
import com.utransparency.model.User;
import com.utransparency.model.UserUniversity;
import com.utransparency.repository.UniversityRepository;
import com.utransparency.service.UniversityService;
import com.utransparency.service.UniversityServiceImpl;
import com.utransparency.service.UserService;
import com.utransparency.service.UserServiceImpl;

@Controller
public class UserController {

 @Autowired
 private UserService userService;
 
 @Autowired
 private UserServiceImpl userServiceImpl;
 
 
 @Autowired
 private UniversityService universityService;
 
 @Autowired
 private UniversityServiceImpl universityServiceImpl;
 
 @RequestMapping(value = {"/", "/index.html"})
 public String index() {
  return "index";
 }
 
 
 @RequestMapping(value= { "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("login");
  return model;
 }
 
 
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  model.setViewName("signup");
  
  return model;
 }
 
 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  User userExists = userService.findUserByEmail(user.getEmail());
  
  if(userExists != null) {
   bindingResult.rejectValue("email", "error.user", "This email already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("/signup");
  } else {
   userService.saveUser(user);
   model.addObject("msg", "User has been registered successfully!");
   model.addObject("user", new User());
   model.setViewName("signup");
  }
  
  return model;
 }
 
 @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  
  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
  model.setViewName("home");
  return model;
 }
 
 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("access_denied");
  return model;
 }
 
 @RequestMapping(value= { "/logoutPage"}, method=RequestMethod.GET)
 public String logoutPage() {
     return "logoutPage";
 }
 
 @RequestMapping(value= { "/admin", "/admin.html"}, method=RequestMethod.GET)
 public String admin() {
     return "admin";
 }
 
 @RequestMapping(value= {"/signupUniv"}, method=RequestMethod.GET)
 public ModelAndView signupUniv() {
  ModelAndView model = new ModelAndView();
  
  University university = new University();
  User user = new User() ;
  UserUniversity userUniversity = new UserUniversity();
  model.addObject("user", user);
  model.addObject("university", university);
  model.addObject("userUniversity", userUniversity);
  model.setViewName("signupUniv");
  
  return model;
 }
 
 @RequestMapping(value= {"/signupUniv"}, method=RequestMethod.POST)
 public ModelAndView createUserUniv(@Valid User user, @Valid University university, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  User userExists = userService.findUserByEmail(user.getEmail());
  
  if(userExists != null) {
   bindingResult.rejectValue("email", "error.user", "This email already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("/signupUniv");
  } else {
   userService.saveUserUniv(user,university);
   model.addObject("msg", "User has been registered successfully!");
   model.addObject("user", new User());
   model.setViewName("signupUniv");
  }
  
  return model;
 }
 
 @ModelAttribute("universities")
 public List<University> universities() {
     return universityServiceImpl.findAll();
 }
 
 
}