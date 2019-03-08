package com.utransparency.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.utransparency.model.Progresivet;
import com.utransparency.repository.ProgresiveRepository;

@Service("progresiveService")
public class ProgresiveServiceImpl {
	
	@Autowired
	private ProgresiveRepository progresiveRepository;
	
	public void saveProgresivet(Progresivet progresivet) {
		
		progresiveRepository.save(progresivet);
		
	}
	
	public List<Progresivet> joinProgresive(){
		
		
		return progresiveRepository.joinProgresive();
		
	}
	
	public List<Progresivet> filterProgresive(Date muaji){
		
		
		return progresiveRepository.filterProgresive(muaji);
		
	}

}
