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
	
	public List<Progresivet> filterProgresive(String muaji, int universityId){
		
		
		return progresiveRepository.filterProgresive(muaji,universityId);
		
	}
	
	public List<Progresivet> filterProgresiveInc(String muaji, int universityId){
		
		
		return progresiveRepository.filterProgresiveInc(muaji,universityId);
		
	}
	
	public List<Progresivet> filterProgresiveExp(String muaji, int universityId){
		
		
		return progresiveRepository.filterProgresiveExp(muaji,universityId);
		
	}
	
	public List<Progresivet> filterProgresiveExpS(String muaji, int universityId){
		
		
		return progresiveRepository.filterProgresiveExpS(muaji,universityId);
		
	}

}
