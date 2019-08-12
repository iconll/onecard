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


/** inventory_detail */
@Entity
@Table(name = "inventory_detail")
public class InventoryDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 应有数量 */
	@Column(name="due_num", length=11)
	private Integer dueNum;
	
	/** 实际数量 */
	@Column(name="real_num", length=11)
	private Integer realNum;
	
	/** 差值 */
	@Column(name="diff", length=11)
	private Integer diff;
	
	/** 备注 */
	@Column(name="remark", length=100)
	private String remark;
	
	/** 商品ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsId")
	private Goods goods;

	/** 盘点单Id */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inventoryId")
	private Inventory inventory;

	/** 操作者ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDueNum() {
		return this.dueNum;
	}

	public void setDueNum(Integer dueNum) {
		this.dueNum = dueNum;
	}
	
	public Integer getRealNum() {
		return this.realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}
	
	public Integer getDiff() {
		return this.diff;
	}

	public void setDiff(Integer diff) {
		this.diff = diff;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
