package com.onecard.system.supermarket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.FetchType;


/** org_group */
@Entity
@Table(name = "org_group")
public class OrgGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** group_id */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_id", length=20)
	private Long groupId;
	
	/** existing_num */
	@Column(name="existing_num", length=20)
	private Long existingNum;
	
	/** group_code */
	@Column(name="group_code", length=255)
	private String groupCode;
	
	/** name */
	@Column(name="name", length=255)
	private String name;
	
	/** node */
	@Column(name="node", length=255)
	private String node;
	
	/** num */
	@Column(name="num", length=20)
	private Long num;
	
	/** parent_node */
	@Column(name="parent_node", length=255)
	private String parentNode;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orgGroup")
	private Set<User> user = new HashSet<User>(0);

	
	
	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public Long getExistingNum() {
		return this.existingNum;
	}

	public void setExistingNum(Long existingNum) {
		this.existingNum = existingNum;
	}
	
	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNode() {
		return this.node;
	}

	public void setNode(String node) {
		this.node = node;
	}
	
	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	public String getParentNode() {
		return this.parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}
	
	public Set<User> getUser() {
		return this.user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
	

}
