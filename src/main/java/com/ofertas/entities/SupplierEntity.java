package com.ofertas.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "supplier")
public class SupplierEntity {

	@Id
	@GeneratedValue
	@Column(name = "document")
	private int document;

	@Column(name = "name")
	private String name;

	@Column(name = "mail")
	private String mail;

	@Column(name = "telephone")
	private String telephone;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	@Column(name = "createDate")
	private Date createDate;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	@Column(name = "modifyDate")
	private Date modifyDate;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "notificationurl")
	private String notificationUrl;

	public SupplierEntity(int document, String name, String mail, String telephone, Date createDate, Date modifyDate,
			boolean enabled, String notificationUrl) {
		super();
		this.document = document;
		this.name = name;
		this.mail = mail;
		this.telephone = telephone;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.enabled = enabled;
		this.notificationUrl = notificationUrl;
	}

	public SupplierEntity() {
	}

	public int getDocument() {
		return document;
	}

	public void setDocument(int document) {
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getNotificationUrl() {
		return notificationUrl;
	}

	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	@Override
	public String toString() {
		return "SupplierEntity [document=" + document + ", name=" + name + ", mail=" + mail + ", telephone=" + telephone
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", enabled=" + enabled
				+ ", notificationUrl=" + notificationUrl + "]";
	}

}
