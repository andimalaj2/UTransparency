package com.utransparency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="university")
public class University {
 
 @Id
// @GeneratedValue(strategy=GenerationType.AUTO)
 @Column(name="universityid")
 private int universityid;
 
 @Column(name="name")
 private String name;



 public int getUniversityid() {
	return universityid;
}

public void setUniversityid(int universityid) {
	this.universityid = universityid;
}

public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }
 
}
 
 