package com.utransparency.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.utransparency.model.Progresivet;
import com.utransparency.model.TypeProgresive;
import com.utransparency.model.University;
import com.utransparency.model.User;
import com.utransparency.model.UserUniversity;
import com.utransparency.model.VirtualProgresive;
import com.utransparency.model.VirtualProgresiveListFormViewModel;
import com.utransparency.service.ProgresiveServiceImpl;
import com.utransparency.service.TypeProgresiveServiceImpl;
import com.utransparency.service.UserServiceImpl;
import com.utransparency.service.UserUniversityServiceImpl;

@Controller
public class UserUtilsController {
	
	@Autowired
	private TypeProgresiveServiceImpl typeProgresiveServiceImpl;
	
	@Autowired
	private ProgresiveServiceImpl  progresiveServiceImpl;
	
	@Autowired
	private UserUniversityServiceImpl userUniversityService;
	
	@Autowired
	private UserServiceImpl userService;
	

	
	
    @RequestMapping(value = "/dateProgres",params = "month", method = RequestMethod.GET)
    @ResponseBody
    public Model getDateProgres(Model model,@RequestParam("month") String month) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	User user = userService.findUserByEmail(userDetails.getUsername());
    	UserUniversity userUniversity = userUniversityService.findByUser(user.getId());
    	
    	List<Progresivet> listProgresivet =  progresiveServiceImpl.filterProgresive(month,userUniversity.getUniversity_id());
		List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();
		 VirtualProgresiveListFormViewModel progresiveForm = null;
		if(listProgresivet.size()>0) {
			  for(int i = 0; i < listProgresivet.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdProgresive(listProgresivet.get(i).getProgresivetID());
				  virtualProgresive.setIdTypeProgresive(listProgresivet.get(i).getTypeProgresiveId());
				  virtualProgresive.setName(typeProgresiveServiceImpl.findById(listProgresivet.get(i).getTypeProgresiveId()).getName());
				  virtualProgresive.setMountPlan(listProgresivet.get(i).getMount());
				  virtualProgresive.setMountFakt(listProgresivet.get(i).getMountFakt());
				  virtualProgresiveList.add(virtualProgresive);
			  }
		   progresiveForm = new VirtualProgresiveListFormViewModel(virtualProgresiveList);
		  
		}
		else {
			List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
			 virtualProgresiveList = new ArrayList<VirtualProgresive>();   
			  for(int i = 0; i < typeProgresive.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdTypeProgresive(typeProgresive.get(i).getTypeprogresiveId());
				  virtualProgresive.setName(typeProgresive.get(i).getName());
				  virtualProgresiveList.add(virtualProgresive);
			  }
			  progresiveForm = new VirtualProgresiveListFormViewModel(virtualProgresiveList);
			
		}
		  progresiveForm.setMonth(month);
		  model.addAttribute("progresiveForm",progresiveForm);

        return model;
    }
	
	
    
    @PostMapping(value = "/edit",params="action=save")
    public String saveProgres(@ModelAttribute VirtualProgresiveListFormViewModel progresiveForm, Model model) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	User user = userService.findUserByEmail(userDetails.getUsername());
    	UserUniversity userUniversity = userUniversityService.findByUser(user.getId());
    	List<VirtualProgresive> virtualProgresiveListT = progresiveForm.getVirtualProgresiveList(); 	  
		  for(int i = 0; i < virtualProgresiveListT.size(); i++) {
				Progresivet progresivet = new Progresivet();
				progresivet.setTypeProgresiveId(virtualProgresiveListT.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListT.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListT.get(i).getMountFakt());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
				Date parsed = null;
				try {
					parsed = format.parse(progresiveForm.getMonth());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parsed.setMonth(parsed.getMonth() + 1);
				progresivet.setReferedDate(parsed);
				progresivet.setUniversityId(userUniversity.getUniversity_id());
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }
		  

		  model.addAttribute("virtualProgresiveList", virtualProgresiveListT);

        return "redirect:/dateProgres?month="+progresiveForm.getMonth();
    }
    
    @PostMapping(value = "/edit", params="action=publiko")
    public String publikoProgres(@ModelAttribute VirtualProgresiveListFormViewModel progresiveForm, Model model) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	User user = userService.findUserByEmail(userDetails.getUsername());
    	UserUniversity userUniversity = userUniversityService.findByUser(user.getId());
    	List<VirtualProgresive> virtualProgresiveListT = progresiveForm.getVirtualProgresiveList(); 	  
		  for(int i = 0; i < virtualProgresiveListT.size(); i++) {
				Progresivet progresivet = new Progresivet();
				progresivet.setTypeProgresiveId(virtualProgresiveListT.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListT.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListT.get(i).getMountFakt());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
				Date parsed = null;
				try {
					parsed = format.parse(progresiveForm.getMonth());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parsed.setMonth(parsed.getMonth() + 1);
				progresivet.setReferedDate(parsed);
				progresivet.setConfirm(true);
				progresivet.setUniversityId(userUniversity.getUniversity_id());
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }
		  

		  model.addAttribute("virtualProgresiveList", virtualProgresiveListT);

		  return "redirect:/dateProgres?month="+progresiveForm.getMonth();
    }
    
    
//    @GetMapping(value = "/allProgres")
//    public String showAll(Model model) {
//    	
//    	List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
//		List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();   
//		  for(int i = 0; i < typeProgresive.size(); i++) {
//			  VirtualProgresive virtualProgresive = new VirtualProgresive();
//			  virtualProgresive.setIdTypeProgresive(typeProgresive.get(i).getTypeprogresiveId());
//			  virtualProgresive.setName(typeProgresive.get(i).getName());
//			  virtualProgresiveList.add(virtualProgresive);
//		  }
//    	
//		  model.addAttribute("virtualProgresiveList", virtualProgresiveList);
//
//        return "allProgres";
//    }
//    
//    @GetMapping(value = "/editProgres")
//    public String showEditForm(Model model) {
//    	
//    	List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll(); 
//		  List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();   
//		  for(int i = 0; i < typeProgresive.size(); i++) {
//			  VirtualProgresive virtualProgresive = new VirtualProgresive();
//			  virtualProgresive.setIdTypeProgresive(typeProgresive.get(i).getTypeprogresiveId());
//			  virtualProgresive.setName(typeProgresive.get(i).getName());
//			  virtualProgresiveList.add(virtualProgresive);
//		  }
//		  
//		  VirtualProgresiveListFormViewModel progresiveForm = new VirtualProgresiveListFormViewModel(virtualProgresiveList);
//		  
//		  model.addAttribute("progresiveForm",progresiveForm);
//
//        return "editProgres";
//    }

         

}
