package com.ofertas.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "offer")
public class OfferEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "requestid")
	private int requestId;

	@Column(name = "total")
	private float total;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "supplierdocument")
	private int supplierDocument;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Transient
	private List<ItemEntity> itemEntities;

	public OfferEntity(int id, int requestId, float total, boolean enabled, int supplierDocument, String name,
			String description, List<ItemEntity> itemEntities) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.total = total;
		this.enabled = enabled;
		this.supplierDocument = supplierDocument;
		this.name = name;
		this.description = description;
		this.itemEntities = itemEntities;
	}

	public OfferEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getSupplierDocument() {
		return supplierDocument;
	}

	public void setSupplierDocument(int supplierDocument) {
		this.supplierDocument = supplierDocument;
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
		return "OfferEntity [id=" + id + ", requestId=" + requestId + ", total=" + total + ", enabled=" + enabled
				+ ", supplierDocument=" + supplierDocument + ", name=" + name + ", description=" + description
				+ ", itemEntities=" + itemEntities + "]";
	}

}
