package com.extend.aoo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Animal {

	private int aminalId;
	private String type;
	private String name;
	
	public int aaa;
	
	@Id
	@GeneratedValue
	public int getAminalId() {
		return aminalId;
	}
	public void setAminalId(int aminalId) {
		this.aminalId = aminalId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
