package com.utransparency.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="progresivet")
public class Progresivet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "progresivet_id")
	private int progresivetID;
	
	
	@Column(name = "mount")
	private float mount;
	
	
	
	
	@Column(name = "refered_date")
	private Date referedDate;
	
	
	@Column(name = "type_mount")
	private int typeMount;
	
	@Column(name = "university_id")
	private int universityId;
	
	

	
	@Column(name ="confirm")
	private Boolean confirm;
	
	
	
//	  @ManyToOne(cascade=CascadeType.ALL)
//	  @JoinColumn(name = "typeprogresive_id", referencedColumnName ="typeprogresive_id") 
//	  private TypeProgresive typeprogresive;
//	
	@Column(name ="typeprogresive_id")
	private int typeprogresiveid;
	 
	  

	public int getProgresivetID() {
		return progresivetID;
	}

	public void setProgresivetID(int progresivetID) {
		this.progresivetID = progresivetID;
	}

	public float getMount() {
		return mount;
	}

	public void setMount(float mount) {
		this.mount = mount;
	}

	public Date getReferedDate() {
		return referedDate;
	}

	public void setReferedDate(Date referedDate) {
		this.referedDate = referedDate;
	}

	public int getTypeMount() {
		return typeMount;
	}

	public void setTypeMount(int typeMount) {
		this.typeMount = typeMount;
	}

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public int getTypeProgresiveId() {
		return typeprogresiveid;
	}

	public void setTypeProgresiveId(int typeProgresiveId) {
		this.typeprogresiveid = typeProgresiveId;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}


	
//	public Progresivet setTypeProgresive(TypeProgresive typeProgresive) {
//		this.typeprogresive = typeProgresive;
//		
//		return this;
//	}
	
//	public void setTypeProgresive(TypeProgresive typeProgresive) {
//		
//		this.typeprogresive = typeProgresive;
//	}

}
