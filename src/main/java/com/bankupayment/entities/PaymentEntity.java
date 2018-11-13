package com.bankupayment.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "payment")
public class PaymentEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "description")
	private String description;
	
	@Column(name = "paymentType")
	private Integer paymentType;

	@Column(name = "originAccount")
	private String originAccount;

	@Column(name = "destinationAccount")
	private String destinationAccount;
	
	@Column(name = "billCode")
	private String billCode;
	
	@Column(name = "swiftCode")
	private String swiftCode;
	
	@Column(name = "total")
	private String total;
	
	@Column(name = "comercialService")
	private String comercialService;
	
	@Column(name = "company")
	private String company;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	@Column(name = "paymentDate")
	private Date paymentDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	@Column(name = "createDate")
	private Date createDate;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	@Column(name = "modifyDate")
	private Date modifyDate;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Transient
	private List<TransationEntity> transations;

	public PaymentEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public String getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getComercialService() {
		return comercialService;
	}

	public void setComercialService(String comercialService) {
		this.comercialService = comercialService;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<TransationEntity> getTransations() {
		return transations;
	}

	public void setTransations(List<TransationEntity> transations) {
		this.transations = transations;
	}

	@Override
	public String toString() {
		return "PaymentEntity [id=" + id + ", description=" + description + ", paymentType=" + paymentType
				+ ", originAccount=" + originAccount + ", destinationAccount=" + destinationAccount + ", billCode="
				+ billCode + ", swiftCode=" + swiftCode + ", total=" + total + ", comercialService=" + comercialService
				+ ", company=" + company + ", paymentDate=" + paymentDate + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", enabled=" + enabled + ", transations=" + transations + "]";
	}

}
