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
	
	
    @GetMapping(value = "/dateProgres")
    public String dateProgres(Model model) {
    	
    	Date muaji = new Date();
    	List<Progresivet> listProgresivet =  progresiveServiceImpl.filterProgresive(muaji);
    	
    	List<TypeProgresive> typeProgresive = typeProgresiveServiceImpl.findAll();
		List<VirtualProgresive> virtualProgresiveList = new ArrayList<VirtualProgresive>();   
		  for(int i = 0; i < listProgresivet.size(); i++) {
			  VirtualProgresive virtualProgresive = new VirtualProgresive();
			  virtualProgresive.setIdProgresive(listProgresivet.get(i).getProgresivetID());
			  virtualProgresive.setIdTypeProgresive(listProgresivet.get(i).getTypeProgresiveId());
			  virtualProgresive.setName(typeProgresiveServiceImpl.findById(listProgresivet.get(i).getTypeProgresiveId()).getName());
			  
			  virtualProgresiveList.add(virtualProgresive);
		  }
    	
		  model.addAttribute("virtualProgresiveList", virtualProgresiveList);

        return "dateProgres";
    }
	
	
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
				progresivet.setMount(virtualProgresiveListT.get(i).getMountPlan());
				progresivet.setTypeMount(1);
				progresivet.setReferedDate(new Date());
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }
		  
		  for(int i = 0; i < virtualProgresiveListT.size(); i++) {
				Progresivet progresivet = new Progresivet();
				progresivet.setTypeProgresiveId(virtualProgresiveListT.get(i).getIdTypeProgresive());
				progresivet.setMount(virtualProgresiveListT.get(i).getMountFakt());
				progresivet.setTypeMount(2);
				progresivet.setReferedDate(new Date());
				progresiveServiceImpl.saveProgresivet(progresivet);  
		  }

		  model.addAttribute("virtualProgresiveList", virtualProgresiveListT);

        return "redirect:/allProgres";
    }
     

}
