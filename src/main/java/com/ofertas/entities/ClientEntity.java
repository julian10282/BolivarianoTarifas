package com.ofertas.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
	@GeneratedValue
	@Column(name = "document")
	private int document;

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastname;

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

	public ClientEntity(int document, String name, String lastname, String mail, String telephone, Date createDate,
			Date modifyDate, boolean enabled) {
		super();
		this.document = document;
		this.name = name;
		this.lastname = lastname;
		this.mail = mail;
		this.telephone = telephone;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.enabled = enabled;
	}

	public ClientEntity() {
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	@Override
	public String toString() {
		return "ClientEntity [document=" + document + ", name=" + name + ", lastname=" + lastname + ", mail=" + mail
				+ ", telephone=" + telephone + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", enabled=" + enabled + "]";
	}

}
