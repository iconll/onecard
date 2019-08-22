package com.onecard.system.supermarket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/** dict */
@Entity
@Table(name = "dict")
public class Dict implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** id */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** code */
	@Column(name="code", length=255)
	private String code;
	
	/** text */
	@Column(name="text", length=255)
	private String text;
	
	/** type */
	@Column(name="type", length=255)
	private String type;
	
	/** value */
	@Column(name="value", length=255)
	private String value;
	
	/** is_load */
	@Column(name="is_load", length=255)
	private String isLoad;
	
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getIsLoad() {
		return this.isLoad;
	}

	public void setIsLoad(String isLoad) {
		this.isLoad = isLoad;
	}
	

}
