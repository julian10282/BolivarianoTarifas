package com.ofertas.entities;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "code")
    private int code;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "catalogcode")
    private int catalogCode;
    
    @Column(name = "enabled")
	private boolean enabled;
    
    @Column(name = "itemid")
	private int itemId;


    public ProductEntity() {
    }


	public ProductEntity(int code, String name, String description, int catalogCode, boolean enabled, int itemId) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.catalogCode = catalogCode;
		this.enabled = enabled;
		this.itemId = itemId;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
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


	public int getCatalogCode() {
		return catalogCode;
	}


	public void setCatalogCode(int catalogCode) {
		this.catalogCode = catalogCode;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
	}


	@Override
	public String toString() {
		return "ProductEntity [code=" + code + ", name=" + name + ", description=" + description + ", catalogCode="
				+ catalogCode + ", enabled=" + enabled + ", itemId=" + itemId + "]";
	}

}
