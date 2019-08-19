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
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;


/** user */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** id */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** login */
	@Column(name="login", length=255)
	private String login;
	
	/** password */
	@Column(name="password", length=255)
	private String password;
	
	/** user_name */
	@Column(name="user_name", length=255)
	private String userName;
	
	/** address */
	@Column(name="address", length=255)
	private String address;
	
	/** job */
	@Column(name="job", length=255)
	private String job;
	
	/** group_id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId")
	private OrgGroup orgGroup;

	/** birth_date */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="birth_date")
	private Date birthDate;
	
	/** city */
	@Column(name="city", length=255)
	private String city;
	
	/** district */
	@Column(name="district", length=255)
	private String district;
	
	/** province */
	@Column(name="province", length=255)
	private String province;
	
	/** street_address */
	@Column(name="street_address", length=255)
	private String streetAddress;
	
	/** state */
	@Column(name="state", length=255)
	private String state;
	
	/** type */
	@Column(name="type", length=255)
	private String type;
	
	/** last_login_date */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="last_login_date")
	private Date lastLoginDate;
	
	/** user_code */
	@Column(name="user_code", length=32)
	private String userCode;
	
	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Card> card = new HashSet<Card>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Goods> goods = new HashSet<Goods>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<InboundBack> inboundBack = new HashSet<InboundBack>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<InboundDetail> inboundDetail = new HashSet<InboundDetail>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<InventoryDetail> inventoryDetail = new HashSet<InventoryDetail>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<OutboundBack> outboundBack = new HashSet<OutboundBack>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<OutboundDetail> outboundDetail = new HashSet<OutboundDetail>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Supplier> supplier = new HashSet<Supplier>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserAssociateRole> userAssociateRole = new HashSet<UserAssociateRole>(0);

	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public OrgGroup getOrgGroup() {
		return this.orgGroup;
	}

	public void setOrgGroup(OrgGroup orgGroup) {
		this.orgGroup = orgGroup;
	}
	
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public Set<Card> getCard() {
		return this.card;
	}

	public void setCard(Set<Card> card) {
		this.card = card;
	}
	
	public Set<Goods> getGoods() {
		return this.goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	
	public Set<InboundBack> getInboundBack() {
		return this.inboundBack;
	}

	public void setInboundBack(Set<InboundBack> inboundBack) {
		this.inboundBack = inboundBack;
	}
	
	public Set<InboundDetail> getInboundDetail() {
		return this.inboundDetail;
	}

	public void setInboundDetail(Set<InboundDetail> inboundDetail) {
		this.inboundDetail = inboundDetail;
	}
	
	public Set<InventoryDetail> getInventoryDetail() {
		return this.inventoryDetail;
	}

	public void setInventoryDetail(Set<InventoryDetail> inventoryDetail) {
		this.inventoryDetail = inventoryDetail;
	}
	
	public Set<OutboundBack> getOutboundBack() {
		return this.outboundBack;
	}

	public void setOutboundBack(Set<OutboundBack> outboundBack) {
		this.outboundBack = outboundBack;
	}
	
	public Set<OutboundDetail> getOutboundDetail() {
		return this.outboundDetail;
	}

	public void setOutboundDetail(Set<OutboundDetail> outboundDetail) {
		this.outboundDetail = outboundDetail;
	}
	
	public Set<Supplier> getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Set<Supplier> supplier) {
		this.supplier = supplier;
	}
	
	public Set<UserAssociateRole> getUserAssociateRole() {
		return this.userAssociateRole;
	}

	public void setUserAssociateRole(Set<UserAssociateRole> userAssociateRole) {
		this.userAssociateRole = userAssociateRole;
	}
	

}
