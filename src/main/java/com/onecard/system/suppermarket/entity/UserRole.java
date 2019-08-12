package com.onecard.system.suppermarket.entity;

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


/** user_role */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** id */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=20)
	private Long id;
	
	/** name */
	@Column(name="name", length=255)
	private String name;
	
	/** role_name */
	@Column(name="role_name", length=255)
	private String roleName;
	
	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
	private Set<RoleAssociateTree> roleAssociateTree = new HashSet<RoleAssociateTree>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRole")
	private Set<UserAssociateRole> userAssociateRole = new HashSet<UserAssociateRole>(0);

	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Set<RoleAssociateTree> getRoleAssociateTree() {
		return this.roleAssociateTree;
	}

	public void setRoleAssociateTree(Set<RoleAssociateTree> roleAssociateTree) {
		this.roleAssociateTree = roleAssociateTree;
	}
	
	public Set<UserAssociateRole> getUserAssociateRole() {
		return this.userAssociateRole;
	}

	public void setUserAssociateRole(Set<UserAssociateRole> userAssociateRole) {
		this.userAssociateRole = userAssociateRole;
	}
	

}
