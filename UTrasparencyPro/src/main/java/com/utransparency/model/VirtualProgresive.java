package com.utransparency.model;

import java.util.Date;

public class VirtualProgresive {
	
	private int idProgresive;
	
	private int idTypeProgresive;
	
	private String name;
	
	private float mountPlan;
	
	private float mountFakt;
	
	private Boolean confirm;
	
	private int subType;
	
	

	public int getIdTypeProgresive() {
		return idTypeProgresive;
	}

	public void setIdTypeProgresive(int idTypeProgresive) {
		this.idTypeProgresive = idTypeProgresive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMountPlan() {
		return mountPlan;
	}

	public void setMountPlan(float mountPlan) {
		this.mountPlan = mountPlan;
	}

	public float getMountFakt() {
		return mountFakt;
	}

	public void setMountFakt(float mountFakt) {
		this.mountFakt = mountFakt;
	}

	public int getIdProgresive() {
		return idProgresive;
	}

	public void setIdProgresive(int idProgresive) {
		this.idProgresive = idProgresive;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}


	
	
	

}
