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
    	
    	List<Progresivet> listProgresivetInc =  progresiveServiceImpl.filterProgresiveInc(month,userUniversity.getUniversity_id());
    	List<Progresivet> listProgresivetExp =  progresiveServiceImpl.filterProgresiveExp(month,userUniversity.getUniversity_id());
    	List<Progresivet> listProgresivetExpS =  progresiveServiceImpl.filterProgresiveExpS(month,userUniversity.getUniversity_id());
		List<VirtualProgresive> virtualProgresiveListInc = new ArrayList<VirtualProgresive>();
		List<VirtualProgresive> virtualProgresiveListExp = new ArrayList<VirtualProgresive>();
		List<VirtualProgresive> virtualProgresiveListExpS = new ArrayList<VirtualProgresive>();
		 VirtualProgresiveListFormViewModel progresiveForm = new VirtualProgresiveListFormViewModel();
		if(listProgresivetInc.size()>0) {
			  for(int i = 0; i < listProgresivetInc.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdProgresive(listProgresivetInc.get(i).getProgresivetID());
				  virtualProgresive.setIdTypeProgresive(listProgresivetInc.get(i).getTypeProgresiveId());
				  virtualProgresive.setName(typeProgresiveServiceImpl.findById(listProgresivetInc.get(i).getTypeProgresiveId()).getName());
				  virtualProgresive.setMountPlan(listProgresivetInc.get(i).getMount());
				  virtualProgresive.setMountFakt(listProgresivetInc.get(i).getMountFakt());
				  virtualProgresive.setConfirm(listProgresivetInc.get(i).getConfirm());
				  virtualProgresive.setSubType(typeProgresiveServiceImpl.findById(listProgresivetInc.get(i).getTypeProgresiveId()).getSubType());
				  virtualProgresiveListInc.add(virtualProgresive);
			  }
			  for(int i = 0; i < listProgresivetExp.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdProgresive(listProgresivetExp.get(i).getProgresivetID());
				  virtualProgresive.setIdTypeProgresive(listProgresivetExp.get(i).getTypeProgresiveId());
				  virtualProgresive.setName(typeProgresiveServiceImpl.findById(listProgresivetExp.get(i).getTypeProgresiveId()).getName());
				  virtualProgresive.setMountPlan(listProgresivetExp.get(i).getMount());
				  virtualProgresive.setMountFakt(listProgresivetExp.get(i).getMountFakt());
				  virtualProgresive.setConfirm(listProgresivetExp.get(i).getConfirm());
				  virtualProgresive.setSubType(typeProgresiveServiceImpl.findById(listProgresivetExp.get(i).getTypeProgresiveId()).getSubType());
				  virtualProgresiveListExp.add(virtualProgresive);
			  }
			  for(int i = 0; i < listProgresivetExpS.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdProgresive(listProgresivetExpS.get(i).getProgresivetID());
				  virtualProgresive.setIdTypeProgresive(listProgresivetExpS.get(i).getTypeProgresiveId());
				  virtualProgresive.setName(typeProgresiveServiceImpl.findById(listProgresivetExpS.get(i).getTypeProgresiveId()).getName());
				  virtualProgresive.setMountPlan(listProgresivetExpS.get(i).getMount());
				  virtualProgresive.setMountFakt(listProgresivetExpS.get(i).getMountFakt());
				  virtualProgresive.setConfirm(listProgresivetExpS.get(i).getConfirm());
				  virtualProgresive.setSubType(typeProgresiveServiceImpl.findById(listProgresivetExpS.get(i).getTypeProgresiveId()).getSubType());
				  virtualProgresiveListExpS.add(virtualProgresive);
			  }
		  
		   progresiveForm.setVirtualProgresiveListInc(virtualProgresiveListInc);
		   progresiveForm.setVirtualProgresiveListExp(virtualProgresiveListExp);
		   progresiveForm.setVirtualProgresiveListExpS(virtualProgresiveListExpS);
		   progresiveForm.setConfirm(listProgresivetInc.get(0).getConfirm());
		  
		}
		else {
			List<TypeProgresive> typeProgresiveInc = typeProgresiveServiceImpl.findBySubtype(1);
			List<TypeProgresive> typeProgresiveExp = typeProgresiveServiceImpl.findBySubtype(2);
			List<TypeProgresive> typeProgresiveExpS = typeProgresiveServiceImpl.findBySubtype(3);
			  for(int i = 0; i < typeProgresiveInc.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdTypeProgresive(typeProgresiveInc.get(i).getTypeprogresiveId());
				  virtualProgresive.setName(typeProgresiveInc.get(i).getName());
				  virtualProgresive.setSubType(typeProgresiveInc.get(i).getSubType());
				  virtualProgresiveListInc.add(virtualProgresive);
			  }
			  for(int i = 0; i < typeProgresiveExp.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdTypeProgresive(typeProgresiveExp.get(i).getTypeprogresiveId());
				  virtualProgresive.setName(typeProgresiveExp.get(i).getName());
				  virtualProgresive.setSubType(typeProgresiveExp.get(i).getSubType());
				  virtualProgresiveListExp.add(virtualProgresive);
			  }
			  for(int i = 0; i < typeProgresiveExpS.size(); i++) {
				  VirtualProgresive virtualProgresive = new VirtualProgresive();
				  virtualProgresive.setIdTypeProgresive(typeProgresiveExpS.get(i).getTypeprogresiveId());
				  virtualProgresive.setName(typeProgresiveExpS.get(i).getName());
				  virtualProgresive.setSubType(typeProgresiveExpS.get(i).getSubType());
				  virtualProgresiveListExpS.add(virtualProgresive);
			  }
			   progresiveForm.setVirtualProgresiveListInc(virtualProgresiveListInc);
			   progresiveForm.setVirtualProgresiveListExp(virtualProgresiveListExp);
			   progresiveForm.setVirtualProgresiveListExpS(virtualProgresiveListExpS);
			
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
    	List<VirtualProgresive> virtualProgresiveListInc = progresiveForm.getVirtualProgresiveListInc(); 
    	List<VirtualProgresive> virtualProgresiveListExp = progresiveForm.getVirtualProgresiveListExp();
    	List<VirtualProgresive> virtualProgresiveListExpS = progresiveForm.getVirtualProgresiveListExpS();
		  for(int i = 0; i < virtualProgresiveListInc.size(); i++) {
				Progresivet progresivet = new Progresivet();
				if(virtualProgresiveListInc.get(i).getIdProgresive()!=0) {
					progresivet.setProgresivetID(virtualProgresiveListInc.get(i).getIdProgresive());
				}				
				progresivet.setTypeProgresiveId(virtualProgresiveListInc.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListInc.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListInc.get(i).getMountFakt());
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
		  for(int i = 0; i < virtualProgresiveListExp.size(); i++) {
				Progresivet progresivet = new Progresivet();
				if(virtualProgresiveListExp.get(i).getIdProgresive()!=0) {
					progresivet.setProgresivetID(virtualProgresiveListExp.get(i).getIdProgresive());
				}				
				progresivet.setTypeProgresiveId(virtualProgresiveListExp.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListExp.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListExp.get(i).getMountFakt());
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
		  for(int i = 0; i < virtualProgresiveListExpS.size(); i++) {
				Progresivet progresivet = new Progresivet();
				if(virtualProgresiveListInc.get(i).getIdProgresive()!=0) {
					progresivet.setProgresivetID(virtualProgresiveListExpS.get(i).getIdProgresive());
				}				
				progresivet.setTypeProgresiveId(virtualProgresiveListExpS.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListExpS.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListExpS.get(i).getMountFakt());
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
		  
		  model.addAttribute("virtualProgresiveList", virtualProgresiveListInc);

        return "redirect:/dateProgres?month="+progresiveForm.getMonth();
    }
    
    @PostMapping(value = "/edit", params="action=publiko")
    public String publikoProgres(@ModelAttribute VirtualProgresiveListFormViewModel progresiveForm, Model model) {
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	User user = userService.findUserByEmail(userDetails.getUsername());
    	UserUniversity userUniversity = userUniversityService.findByUser(user.getId());
    	List<VirtualProgresive> virtualProgresiveListInc = progresiveForm.getVirtualProgresiveListInc(); 
    	List<VirtualProgresive> virtualProgresiveListExp = progresiveForm.getVirtualProgresiveListExp();
    	List<VirtualProgresive> virtualProgresiveListExpS = progresiveForm.getVirtualProgresiveListExpS();
		  for(int i = 0; i < virtualProgresiveListInc.size(); i++) {
				Progresivet progresivet = new Progresivet();
				if(virtualProgresiveListInc.get(i).getIdProgresive()!=0) {
					progresivet.setProgresivetID(virtualProgresiveListInc.get(i).getIdProgresive());
				}				
				progresivet.setTypeProgresiveId(virtualProgresiveListInc.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListInc.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListInc.get(i).getMountFakt());
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
				progresivet.setConfirm(true);
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }
		  for(int i = 0; i < virtualProgresiveListExp.size(); i++) {
				Progresivet progresivet = new Progresivet();
				if(virtualProgresiveListExp.get(i).getIdProgresive()!=0) {
					progresivet.setProgresivetID(virtualProgresiveListExp.get(i).getIdProgresive());
				}				
				progresivet.setTypeProgresiveId(virtualProgresiveListExp.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListExp.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListExp.get(i).getMountFakt());
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
				progresivet.setConfirm(true);
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }
		  for(int i = 0; i < virtualProgresiveListExpS.size(); i++) {
				Progresivet progresivet = new Progresivet();
				if(virtualProgresiveListInc.get(i).getIdProgresive()!=0) {
					progresivet.setProgresivetID(virtualProgresiveListExpS.get(i).getIdProgresive());
				}				
				progresivet.setTypeProgresiveId(virtualProgresiveListExpS.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListExpS.get(i).getMountPlan());
				progresivet.setMountFakt(virtualProgresiveListExpS.get(i).getMountFakt());
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
				progresivet.setConfirm(true);
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }
		  
		  model.addAttribute("virtualProgresiveList", virtualProgresiveListInc);

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
