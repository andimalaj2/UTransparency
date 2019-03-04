package com.utransparency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_university")
public class UserUniversity {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="useruniversity_id")
	 private int userUniversityId;

	@Column(name = "user_id")
	private int user_id;
	

	@Column(name = "university_id")
	private int university_id;
	
	
	public UserUniversity(int user_id, int university_id) {
		
		this.user_id = user_id;
		this.university_id = university_id;
	}

	public UserUniversity() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return userUniversityId;
	}
	
	public void setId(int userUniversityId) {
		 this.userUniversityId = userUniversityId ;
	}
	

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getUniversity_id() {
		return university_id;
	}

	public void setUniversity_id(int university_id) {
		this.university_id = university_id;
	}

	
	
}
