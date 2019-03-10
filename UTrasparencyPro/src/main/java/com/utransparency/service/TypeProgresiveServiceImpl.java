package com.utransparency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utransparency.model.TypeProgresive;
import com.utransparency.repository.TypeProgresiveRepository;

@Service("typeProgresiveService")
public class TypeProgresiveServiceImpl implements TypeProgresiveService {
	
	@Autowired
	private TypeProgresiveRepository typeProgresiveRepository;
	
	public List<TypeProgresive> findAll(){
		
		return typeProgresiveRepository.findAll();
		
	}
	
	public TypeProgresive findById(int idType) {
		return typeProgresiveRepository.findById(idType);
	}
	
	public List<TypeProgresive> findBySubtype(int idType) {
		return typeProgresiveRepository.findBySubtype(idType);
	}
	

}
