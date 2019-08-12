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


/** outbound_detail */
@Entity
@Table(name = "outbound_detail")
public class OutboundDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 商品ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsId")
	private Goods goods;

	/** 数量 */
	@Column(name="num", length=11)
	private Integer num;
	
	/** 单价 */
	@Column(name="price", precision=10, scale=2)
	private Double price;
	
	/** 总价 */
	@Column(name="sum_price", precision=10, scale=2)
	private Double sumPrice;
	
	/** 出库单ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outboundId")
	private Outbound outbound;

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
	
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSumPrice() {
		return this.sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	public Outbound getOutbound() {
		return this.outbound;
	}

	public void setOutbound(Outbound outbound) {
		this.outbound = outbound;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
