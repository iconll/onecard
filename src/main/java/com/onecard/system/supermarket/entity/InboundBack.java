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


/** inbound_back */
@Entity
@Table(name = "inbound_back")
public class InboundBack implements Serializable {

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

	/** 退货数量 */
	@Column(name="num", length=11)
	private Integer num;
	
	/** 单价 */
	@Column(name="price", precision=10, scale=2)
	private Double price;
	
	/** 总价 */
	@Column(name="sum_price", precision=10, scale=2)
	private Double sumPrice;
	
	/** 备注 */
	@Column(name="remark", length=200)
	private String remark;
	
	/** 采购单ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inboundId")
	private Inbound inbound;

	/** 操作者ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/** 退货时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	/** 入库明细id */
	@Column(name="inbound_detail_id", length=11)
	private Integer inboundDetailId;
	
	
	
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
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Inbound getInbound() {
		return this.inbound;
	}

	public void setInbound(Inbound inbound) {
		this.inbound = inbound;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getInboundDetailId() {
		return this.inboundDetailId;
	}

	public void setInboundDetailId(Integer inboundDetailId) {
		this.inboundDetailId = inboundDetailId;
	}
	

}
