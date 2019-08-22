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


/** inventory */
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 编号 */
	@Column(name="no", length=20)
	private String no;
	
	/** 开始时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="start_time")
	private Date startTime;
	
	/** 结束时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="end_time")
	private Date endTime;
	
	/** 总差值 */
	@Column(name="diff", length=11)
	private Integer diff;
	
	/** 备注 */
	@Column(name="remark", length=0)
	private String remark;
	
	/** 状态（0:正常，1:异常） */
	@Column(name="status", length=11)
	private Integer status;
	
	/** 完成状态（0：未完成，1：已完成） */
	@Column(name="state", length=11)
	private Integer state;
	
	/** 进度（百分比） */
	@Column(name="schedule", length=20)
	private String schedule;
	
	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	/** 更新时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="update_time")
	private Date updateTime;
	
	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inventory")
	private Set<InventoryDetail> inventoryDetail = new HashSet<InventoryDetail>(0);

	
	
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
	
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Set<InventoryDetail> getInventoryDetail() {
		return this.inventoryDetail;
	}

	public void setInventoryDetail(Set<InventoryDetail> inventoryDetail) {
		this.inventoryDetail = inventoryDetail;
	}
	

}
