package com.interval.dao.models;

// Generated Aug 14, 2015 6:24:11 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private Integer id;
	private String name;
	private String description;
	private Date createdTime;
	private Date updatedTime;
	private Set<Product> products = new HashSet<Product>(0);

	public Category() {
	}

	public Category(String name, String description, Date createdTime,
			Date updatedTime, Set<Product> products) {
		this.name = name;
		this.description = description;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.products = products;
	}

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
