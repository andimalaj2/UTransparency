package com.utransparency.model;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class ProgresiveTypeProgresive {
	
	@Autowired
	private Progresivet progresivet;
	
	@Autowired
	private TypeProgresive typeProgresive;

}
