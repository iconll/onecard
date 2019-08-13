package com.onecard.system.suppermarket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;


/** org_user */
@Entity
@Table(name = "org_user")
public class OrgUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 用户ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/** 组织机构ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orgId")
	private OrgGroup orgGroup;

	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public OrgGroup getOrgGroup() {
		return this.orgGroup;
	}

	public void setOrgGroup(OrgGroup orgGroup) {
		this.orgGroup = orgGroup;
	}
	

}
