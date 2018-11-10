package com.ofertas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "requestid")
	private int requestId;
	@Column(name = "offerid")
	private int offerId;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "price")
	private long price;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "product_id")
	private int productId;

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public ItemEntity(int id, int requestId, int offerId, int quantity, long price, boolean enabled, int productId) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.offerId = offerId;
		this.quantity = quantity;
		this.price = price;
		this.enabled = enabled;
		this.productId = productId;
	}

	public ItemEntity() {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ItemEntity [id=" + id + ", requestId=" + requestId + ", offerId=" + offerId + ", quantity=" + quantity
				+ ", price=" + price + ", enabled=" + enabled + ", productId=" + productId + "]";
	}

}
