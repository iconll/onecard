package com.onecard.system.suppermarket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/** test */
@Entity
@Table(name = "test")
public class Test implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** id */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** name */
	@Column(name="name", length=255)
	private String name;
	
	/** age */
	@Column(name="age", length=11)
	private Integer age;
	
	/** test */
	@Column(name="test", length=255)
	private String test;
	
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getTest() {
		return this.test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	

}
