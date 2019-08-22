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
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.FetchType;


/** outbound */
@Entity
@Table(name = "outbound")
public class Outbound implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 单号 */
	@Column(name="no", length=50)
	private String no;
	
	/** 类型（0:零售,1:领用） */
	@Column(name="type", length=1)
	private Integer type;
	
	/** 付款状态（0:未付款，1:已付款） */
	@Column(name="payment", length=1)
	private Integer payment;
	
	/** 总数量 */
	@Column(name="num", length=11)
	private Integer num;
	
	/** 出库时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="out_time")
	private Date outTime;
	
	/** 备注 */
	@Column(name="remark", length=200)
	private String remark;
	
	/** 出库总价 */
	@Column(name="sum_price", precision=10, scale=2)
	private Double sumPrice;
	
	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "outbound")
	private Set<OutboundBack> outboundBack = new HashSet<OutboundBack>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "outbound")
	private Set<OutboundDetail> outboundDetail = new HashSet<OutboundDetail>(0);

	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getPayment() {
		return this.payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Date getOutTime() {
		return this.outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Double getSumPrice() {
		return this.sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
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
	

}
