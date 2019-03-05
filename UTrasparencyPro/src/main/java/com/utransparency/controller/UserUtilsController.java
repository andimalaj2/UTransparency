package com.utransparency.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	  //TypeProgresive typeProgresive = new TypeProgresive();
	  
	  List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
	  Map<Map<Integer,String>,Integer> progresiveHash = new HashMap();
	  for (int i = 0; i < typeProgresive.size(); i++) {
		  Map<Integer,String> hashTemp = new HashMap();
		  hashTemp.put(typeProgresive.get(i).getTypeprogresiveId(),  typeProgresive.get(i).getName());
		  progresiveHash.put(hashTemp, null);
	  }
	  
	  model.addObject("progresiveHash",progresiveHash);
	  model.addObject("progresivet",progresivet);
	  model.addObject("typeProgresive",typeProgresive);
	  model.setViewName("uploadProgres");
	  return model;
	 }
	 
	 @RequestMapping(value= { "/uploadProgres"}, method=RequestMethod.POST)
	 public ModelAndView upload(@Valid Progresivet progresive,  BindingResult bindingResult) {
	// public ModelAndView upload(@Valid Integer universityid,@Valid Date date, @Valid HashMap<TypeProgresive,Integer> progresiveHash,  BindingResult bindingResult) {
	  ModelAndView model = new ModelAndView();
	  
//	  List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
//	  Map<Integer,Integer> progresiveHash1 = new HashMap();
//	  Date referedDate = new Date();
//	  
//	  for (int i = 0; i < typeProgresive.size(); i++) {
//		  progresiveHash1.put(typeProgresive.get(i).getTypeprogresiveId(), i+100);
//	  }
//	  
//		for (Map.Entry<Integer, Integer> entry : progresiveHash1.entrySet() ) {
//			Progresivet progresivet = new Progresivet();
//			progresivet.setTypeProgresiveId(entry.getKey());
//			progresivet.setTypeMount(entry.getValue());
//			progresivet.setReferedDate(referedDate);
//			progresiveServiceImpl.saveProgresivet(progresivet);    
//		}
	  	  
	  
	  if(bindingResult.hasErrors()) {
		   model.setViewName("/uploadProgres");
		  } else {
			  
	  	   
		   model.setViewName("uploadProgres");
		  }
		  
		  return model;
	 }
	 
	 
	 @ModelAttribute("typeProgresivet")
	 public List<TypeProgresive> typeprogresivet() {
	     return typeProgresiveServiceImpl.findAll();
	 }
	 
//	 @ModelAttribute("progresivetRow")
//	 public List<Progresivet> progresivetRow() {
//	     return progresiveServiceImpl.joinProgresive();
//	 }

}
