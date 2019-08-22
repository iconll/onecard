package com.onecard.system.supermarket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;


/** supplier */
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 供货商名称 */
	@Column(name="name", length=50)
	private String name;
	
	/** 联系电话 */
	@Column(name="tel", length=20)
	private String tel;
	
	/** 地区 */
	@Column(name="area", length=100)
	private String area;
	
	/** 地址 */
	@Column(name="address", length=200)
	private String address;
	
	/** 联系人 */
	@Column(name="contact", length=20)
	private String contact;
	
	/** 备注 */
	@Column(name="remark", length=200)
	private String remark;
	
	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	/** 添加人 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
	private Set<Goods> goods = new HashSet<Goods>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
	private Set<Inbound> inbound = new HashSet<Inbound>(0);

	
	
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
	
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<Goods> getGoods() {
		return this.goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	
	public Set<Inbound> getInbound() {
		return this.inbound;
	}

	public void setInbound(Set<Inbound> inbound) {
		this.inbound = inbound;
	}
	

}
