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


/** role_associate_tree */
@Entity
@Table(name = "role_associate_tree")
public class RoleAssociateTree implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** role_id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private UserRole userRole;

	/** tree_id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "treeId")
	private Tree tree;

	
	
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public Tree getTree() {
		return this.tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}
	

}
