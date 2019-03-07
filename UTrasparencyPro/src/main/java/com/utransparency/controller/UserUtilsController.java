package com.utransparency.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.utransparency.model.Progresivet;
import com.utransparency.model.TypeProgresive;
import com.utransparency.model.VirtualProgresive;
import com.utransparency.model.VirtualProgresiveListFormViewModel;
import com.utransparency.service.ProgresiveServiceImpl;
import com.utransparency.service.TypeProgresiveServiceImpl;

@Controller
public class UserUtilsController {
	
	@Autowired
	private TypeProgresiveServiceImpl typeProgresiveServiceImpl;
	
	@Autowired
	private ProgresiveServiceImpl  progresiveServiceImpl;
	
	
    @GetMapping(value = "/allProgres")
    public String showAll(Model model) {
    	
    	List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
		List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();   
		  for(int i = 0; i < typeProgresive.size(); i++) {
			  VirtualProgresive virtualProgresive = new VirtualProgresive();
			  virtualProgresive.setIdTypeProgresive(typeProgresive.get(i).getTypeprogresiveId());
			  virtualProgresive.setName(typeProgresive.get(i).getName());
			  virtualProgresiveList.add(virtualProgresive);
		  }
    	
		  model.addAttribute("virtualProgresiveList", virtualProgresiveList);

        return "allProgres";
    }
    
    @GetMapping(value = "/editProgres")
    public String showEditForm(Model model) {
    	
    	List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll(); 
		  List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();   
		  for(int i = 0; i < typeProgresive.size(); i++) {
			  VirtualProgresive virtualProgresive = new VirtualProgresive();
			  virtualProgresive.setIdTypeProgresive(typeProgresive.get(i).getTypeprogresiveId());
			  virtualProgresive.setName(typeProgresive.get(i).getName());
			  virtualProgresiveList.add(virtualProgresive);
		  }
		  
		  VirtualProgresiveListFormViewModel progresiveForm = new VirtualProgresiveListFormViewModel(virtualProgresiveList);
		  
		  model.addAttribute("progresiveForm",progresiveForm);

        return "editProgres";
    }

    @PostMapping(value = "/save")
    public String saveProgres(@ModelAttribute VirtualProgresiveListFormViewModel progresiveForm, Model model) {
    	List<VirtualProgresive> virtualProgresiveListT = progresiveForm.getVirtualProgresiveList();
  	  
		  for(int i = 0; i < virtualProgresiveListT.size(); i++) {
				Progresivet progresivet = new Progresivet();
	
				progresivet.setTypeProgresiveId(virtualProgresiveListT.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListT.get(i).getMountFakt());
				progresivet.setReferedDate(new Date());
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }

		  model.addAttribute("virtualProgresiveList", virtualProgresiveListT);

        return "redirect:/allProgres";
    }
    
    
    
    
    
	
	
	
	
	 @RequestMapping(value= { "/uploadProgres"}, method=RequestMethod.GET)
	 public ModelAndView upload() {
		  ModelAndView model = new ModelAndView();
		  
		  List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
		
		  
		 
		  
		  List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();   
		  for(int i = 0; i < typeProgresive.size(); i++) {
			  VirtualProgresive virtualProgresive = new VirtualProgresive();
			  virtualProgresive.setIdTypeProgresive(typeProgresive.get(i).getTypeprogresiveId());
			  virtualProgresive.setName(typeProgresive.get(i).getName());
			  virtualProgresiveList.add(virtualProgresive);
		  }
	  
	
	  model.addObject("virtualProgresiveList",virtualProgresiveList);
//	  model.addObject("muaji", new Date());
	  model.setViewName("uploadProgres");
	  return model;
	 }
	 
	 @RequestMapping(value= { "/uploadProgres"}, method=RequestMethod.POST)
	
	 public ModelAndView uploadPList(@ModelAttribute VirtualProgresiveListFormViewModel progresiveForm) {

		 
		 ModelAndView model = new ModelAndView();
	   
	//	  List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
	//	  List<VirtualProgresive> virtualProgresiveListT = new ArrayList<VirtualProgresive>();  
	//	  for(int i = 0; i < typeProgresive.size(); i++) {
	//		  VirtualProgresive virtualProgresive = new VirtualProgresive();
	//		  virtualProgresive.setIdTypeProgresive(typeProgresive.get(i).getTypeprogresiveId());
	//		  virtualProgresive.setName(typeProgresive.get(i).getName());
	//		  virtualProgresive.setMountFakt(100);
	//		  virtualProgresive.setMountPlan(200);
	//		  virtualProgresiveListT.add(virtualProgresive);
	//	  }
		 
		 
		 
		 
		 List<VirtualProgresive> virtualProgresiveListT = progresiveForm.getVirtualProgresiveList();
	  
		  for(int i = 0; i < virtualProgresiveListT.size(); i++) {
				Progresivet progresivet = new Progresivet();
	
				progresivet.setTypeProgresiveId(virtualProgresiveListT.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListT.get(i).getMountFakt());
				progresivet.setReferedDate(new Date());
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }
	  
	  
//	  if(bindingResult.hasErrors()) {
//		   model.setViewName("/uploadProgres");
//		  } else {
//			  
//	  	   
//		   model.setViewName("uploadProgres");
//		  }
//		  
//		  return model;
	  
	  model.setViewName("uploadProgres");
	  return model;
	  
	 }
	 
	 
//	 @ModelAttribute("typeProgresivet")
//	 public List<TypeProgresive> typeprogresivet() {
//	     return typeProgresiveServiceImpl.findAll();
//	 }
//	 
//	 @ModelAttribute("progresivetRow")
//	 public List<Progresivet> progresivetRow() {
//	     return progresiveServiceImpl.joinProgresive();
//	 }
	 
	 
//	 @ModelAttribute("progresiveHash")
//	 public Map<Map<Integer,String>,Integer> progresiveHash() {
//		 
//		 List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
//		  Map<Map<Integer,String>,Integer> progresiveHash = new HashMap();
//		  for (int i = 0; i < typeProgresive.size(); i++) {
//			  Map<Integer,String> hashTemp = new HashMap();
//			  hashTemp.put(typeProgresive.get(i).getTypeprogresiveId(),  typeProgresive.get(i).getName());
//			  progresiveHash.put(hashTemp, null);
//		  }
//	     return progresiveHash;
//	 }
	 
	 

}
