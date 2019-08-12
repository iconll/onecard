package com.onecard.system.suppermarket.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/** bank_info */
@Entity
@Table(name = "bank_info")
public class BankInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/** 主键ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", length=11)
	private Integer id;
	
	/** 网点名称 */
	@Column(name="bank_name", length=64)
	private String bankName;
	
	/** 网点代码 */
	@Column(name="bank_code", length=64)
	private String bankCode;
	
	/** 联系人 */
	@Column(name="contacts", length=32)
	private String contacts;
	
	/** 电话号码 */
	@Column(name="phone_code", length=20)
	private String phoneCode;
	
	/** 所属管辖支行 */
	@Column(name="parent_id", length=32)
	private String parentId;
	
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

}
