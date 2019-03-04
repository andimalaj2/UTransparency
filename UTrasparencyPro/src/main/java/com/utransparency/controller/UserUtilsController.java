package com.utransparency.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utransparency.model.Progresivet;
import com.utransparency.model.TypeProgresive;
import com.utransparency.model.User;
import com.utransparency.service.ProgresiveServiceImpl;
import com.utransparency.service.TypeProgresiveServiceImpl;

@Controller
public class UserUtilsController {
	
	@Autowired
	private TypeProgresiveServiceImpl typeProgresiveServiceImpl;
	
	@Autowired
	private ProgresiveServiceImpl  progresiveServiceImpl;
	
	 @RequestMapping(value= { "/uploadProgres"}, method=RequestMethod.GET)
	 public ModelAndView login() {
	  ModelAndView model = new ModelAndView();
	  Progresivet progresivet = new Progresivet();
	  TypeProgresive typeProgresive = new TypeProgresive();
	  model.addObject("progresivet",progresivet);
	  model.addObject("typeProgresive",typeProgresive);
	  model.setViewName("uploadProgres");
	  return model;
	 }
	 
	 @RequestMapping(value= { "/uploadProgres"}, method=RequestMethod.POST)
	 public ModelAndView upload(@Valid Progresivet progresive,  BindingResult bindingResult) {
	  ModelAndView model = new ModelAndView();
	  
	  if(bindingResult.hasErrors()) {
		   model.setViewName("/uploadProgres");
		  } else {
			  
	  	   progresiveServiceImpl.saveProgresivet(progresive);
		   model.setViewName("uploadProgres");
		  }
		  
		  return model;
	 }
	 
	 
	 @ModelAttribute("typeProgresivet")
	 public List<TypeProgresive> typeprogresivet() {
	     return typeProgresiveServiceImpl.findAll();
	 }
	 
	 @ModelAttribute("progresivetRow")
	 public List<Progresivet> progresivetRow() {
	     return progresiveServiceImpl.joinProgresive();
	 }

}
