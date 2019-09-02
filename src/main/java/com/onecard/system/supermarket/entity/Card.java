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


/** card */
@Entity
@Table(name = "card")
public class Card implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 卡号 */
	@Column(name="card_no", length=50)
	private String cardNo;
	
	/** 持卡人姓名 */
	@Column(name="name", length=50)
	private String name;
	
	/** 余额 */
	@Column(name="balance", precision=10, scale=2)
	private Double balance;
	
	/** 卡类型（参考数据字典card_type） */
	@Column(name="type", length=11)
	private Integer type;
	
	/** 卡状态（参考数据字典card_status） */
	@Column(name="status", length=11)
	private Integer status;
	
	/** 累计存款（作为冗余字段便于查询） */
	@Column(name="sum_deposit", precision=10, scale=2)
	private Double sumDeposit;
	
	/** 累计支出（作为冗余字段便于查询） */
	@Column(name="sum_exp", precision=10, scale=2)
	private Double sumExp;
	
	/** 开卡时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	/** 销卡时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="destroy_time")
	private Date destroyTime;
	
	/** 开卡人ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Double getSumDeposit() {
		return this.sumDeposit;
	}

	public void setSumDeposit(Double sumDeposit) {
		this.sumDeposit = sumDeposit;
	}
	
	public Double getSumExp() {
		return this.sumExp;
	}

	public void setSumExp(Double sumExp) {
		this.sumExp = sumExp;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getDestroyTime() {
		return this.destroyTime;
	}

	public void setDestroyTime(Date destroyTime) {
		this.destroyTime = destroyTime;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
