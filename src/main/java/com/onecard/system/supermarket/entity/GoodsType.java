package com.onecard.system.supermarket.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;


/** goods_type */
@Entity
@Table(name = "goods_type")
public class GoodsType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 类型名称 */
	@Column(name="name", length=20)
	private String name;

	/** 类型名称 */
	@Column(name="code", length=30)
	private String code;
	
	/** 父类ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private GoodsType goodsType;

	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goodsType")
	private Set<Goods> goods = new HashSet<Goods>(0);

	
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public GoodsType getGoodsType() {
		return this.goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Set<Goods> getGoods() {
		return this.goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	

}
