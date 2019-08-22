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


/** inbound */
@Entity
@Table(name = "inbound")
public class Inbound implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 入库总数 */
	@Column(name="num", length=11)
	private Integer num;
	
	/** 单号 */
	@Column(name="no", length=30)
	private String no;
	
	/** 供应商ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	/** 入库时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	/** 采购时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="purchase_time")
	private Date purchaseTime;
	
	/** 是否结算（0:未结算，1:已结算） */
	@Column(name="is_settle", length=1)
	private Integer isSettle;
	
	/** 总价 */
	@Column(name="sum_price", precision=10, scale=2)
	private Double sumPrice;
	
	/** 备注 */
	@Column(name="remark", length=100)
	private String remark;
	
	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inbound")
	private Set<InboundBack> inboundBack = new HashSet<InboundBack>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inbound")
	private Set<InboundDetail> inboundDetail = new HashSet<InboundDetail>(0);

	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getPurchaseTime() {
		return this.purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	public Integer getIsSettle() {
		return this.isSettle;
	}

	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
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
	

}
