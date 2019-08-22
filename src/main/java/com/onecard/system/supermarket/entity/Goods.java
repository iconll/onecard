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


/** goods */
@Entity
@Table(name = "goods")
public class Goods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 商品名称 */
	@Column(name="name", length=200)
	private String name;
	
	/** 商品类型ID */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsTypeId")
	private GoodsType goodsType;

	/** 编码 */
	@Column(name="code", length=200)
	private String code;
	
	/** 库存量 */
	@Column(name="num", length=11)
	private Integer num;
	
	/** 单价 */
	@Column(name="price", precision=10, scale=2)
	private Double price;
	
	/** 单位 */
	@Column(name="unit", length=20)
	private String unit;
	
	/** 规格 */
	@Column(name="specs", length=30)
	private String specs;
	
	/** 首选供应商 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	/** 税率 */
	@Column(name="tax_rate", precision=10, scale=2)
	private Double taxRate;
	
	/** 描述 */
	@Column(name="desp", length=200)
	private String desp;
	
	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="create_time")
	private Date createTime;
	
	/** 库存总价值 */
	@Column(name="sum_price", precision=10, scale=0)
	private Double sumPrice;
	
	/** 商品图片 */
	@Column(name="image", length=50)
	private String image;
	
	/** 添加人 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	/** 多表关系映射 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<InboundBack> inboundBack = new HashSet<InboundBack>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<InboundDetail> inboundDetail = new HashSet<InboundDetail>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<InventoryDetail> inventoryDetail = new HashSet<InventoryDetail>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<OutboundBack> outboundBack = new HashSet<OutboundBack>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	private Set<OutboundDetail> outboundDetail = new HashSet<OutboundDetail>(0);

	
	
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
	
	public GoodsType getGoodsType() {
		return this.goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getSpecs() {
		return this.specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Double getSumPrice() {
		return this.sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
	

}
