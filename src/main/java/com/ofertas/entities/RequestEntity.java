package com.ofertas.entities;

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
@Table(name = "request")
public class RequestEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "clientdocument")
	private String clientDocument;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	@Column(name = "startdate")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Bogota")
	@Column(name = "finaldate")
	private Date finalDate;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Transient
	private List<ItemEntity> itemEntities;

	public RequestEntity(int id, String clientDocument, Date startDate, Date finalDate, boolean enabled, String name,
			String description, List<ItemEntity> itemEntities) {
		super();
		this.id = id;
		this.clientDocument = clientDocument;
		this.startDate = startDate;
		this.finalDate = finalDate;
		this.enabled = enabled;
		this.name = name;
		this.description = description;
		this.itemEntities = itemEntities;
	}

	public RequestEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientDocument() {
		return clientDocument;
	}

	public void setClientDocument(String clientDocument) {
		this.clientDocument = clientDocument;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ItemEntity> getItemEntities() {
		return itemEntities;
	}

	public void setItemEntities(List<ItemEntity> itemEntities) {
		this.itemEntities = itemEntities;
	}

	@Override
	public String toString() {
		return "RequestEntity [id=" + id + ", clientDocument=" + clientDocument + ", startDate=" + startDate
				+ ", finalDate=" + finalDate + ", enabled=" + enabled + ", name=" + name + ", description="
				+ description + ", itemEntities=" + itemEntities + "]";
	}

}
