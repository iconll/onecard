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


/** tree */
@Entity
@Table(name = "tree")
public class Tree implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** id */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=20)
	private Long id;
	
	/** code */
	@Column(name="code", length=255)
	private String code;
	
	/** icon */
	@Column(name="icon", length=255)
	private String icon;
	
	/** name */
	@Column(name="name", length=255)
	private String name;
	
	/** p_id */
	@Column(name="p_id", length=20)
	private Long pId;
	
	/** tree_order */
	@Column(name="tree_order", length=20)
	private Long treeOrder;
	
	/** url */
	@Column(name="url", length=255)
	private String url;
	
	/** state */
	@Column(name="state", length=10)
	private String state;
	
	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tree")
	private Set<RoleAssociateTree> roleAssociateTree = new HashSet<RoleAssociateTree>(0);

	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getPId() {
		return this.pId;
	}

	public void setPId(Long pId) {
		this.pId = pId;
	}
	
	public Long getTreeOrder() {
		return this.treeOrder;
	}

	public void setTreeOrder(Long treeOrder) {
		this.treeOrder = treeOrder;
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Set<RoleAssociateTree> getRoleAssociateTree() {
		return this.roleAssociateTree;
	}

	public void setRoleAssociateTree(Set<RoleAssociateTree> roleAssociateTree) {
		this.roleAssociateTree = roleAssociateTree;
	}
	

}
