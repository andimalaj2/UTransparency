package com.utransparency.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.utransparency.model.Progresivet;
import com.utransparency.model.TypeProgresive;
import com.utransparency.model.User;
import com.utransparency.model.UserUniversity;
import com.utransparency.model.VirtualProgresive;
import com.utransparency.model.VirtualProgresiveListFormViewModel;
import com.utransparency.service.ProgresiveServiceImpl;
import com.utransparency.service.TypeProgresiveServiceImpl;
import com.utransparency.service.UserServiceImpl;
import com.utransparency.service.UserUniversityServiceImpl;

@Controller
public class ExternalAreaController {
	
	
	@Autowired
	private TypeProgresiveServiceImpl typeProgresiveServiceImpl;
	
	@Autowired
	private ProgresiveServiceImpl  progresiveServiceImpl;
	
	@Autowired
	private UserUniversityServiceImpl userUniversityService;
	
	@Autowired
	private UserServiceImpl userService;
	
	
	
	@RequestMapping(value = "/public_progres",params = "month", method = RequestMethod.GET)
    @ResponseBody
    public Model publicProgres(Model model,@RequestParam("month") String month) {
    	
//    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//    	User user = userService.findUserByEmail(userDetails.getUsername());
//    	UserUniversity userUniversity = userUniversityService.findByUser(user.getId());
		
		int university = 3;
    	
    	List<Progresivet> listProgresivetInc =  progresiveServiceImpl.filterProgresiveInc(month,university);
    	List<Progresivet> listProgresivetExp =  progresiveServiceImpl.filterProgresiveExp(month,university);
    	List<Progresivet> listProgresivetExpS =  progresiveServiceImpl.filterProgresiveExpS(month,university);
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
	

}
