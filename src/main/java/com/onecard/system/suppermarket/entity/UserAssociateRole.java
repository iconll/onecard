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


/** user_associate_role */
@Entity
@Table(name = "user_associate_role")
public class UserAssociateRole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** user_id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/** role_id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private UserRole userRole;

	
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	

}
