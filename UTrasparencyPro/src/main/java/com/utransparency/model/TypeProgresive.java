package com.utransparency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_progresive")
public class TypeProgresive {
	
	@Id
	@Column(name = "typeprogresive_id")
	private int typeprogresiveId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sub_type")
	private int subType;
	
	@Column(name = "seriali_buxhet")
	private String serialiBuxhet;

	public int getTypeprogresiveId() {
		return typeprogresiveId;
	}

	public void setTypeprogresiveId(int typeprogresiveId) {
		this.typeprogresiveId = typeprogresiveId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public String getSerialiBuxhet() {
		return serialiBuxhet;
	}

	public void setSerialiBuxhet(String serialiBuxhet) {
		this.serialiBuxhet = serialiBuxhet;
	}

}
