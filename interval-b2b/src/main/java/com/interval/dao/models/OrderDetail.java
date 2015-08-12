package com.interval.dao.models;

// Generated Aug 12, 2015 7:36:52 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OrderDetail generated by hbm2java
 */
public class OrderDetail implements java.io.Serializable {

	private Integer id;
	private OrderStatus orderStatus;
	private int userId;
	private Double total;
	private Double subTotal;
	private Integer lineItemCount;
	private Double discountTotal;
	private String promoCode;
	private Date createdTime;
	private Date updatedTime;
	private Double taxTotal;
	private Set<Invoice> invoices = new HashSet<Invoice>(0);
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);

	public OrderDetail() {
	}

	public OrderDetail(OrderStatus orderStatus, int userId) {
		this.orderStatus = orderStatus;
		this.userId = userId;
	}

	public OrderDetail(OrderStatus orderStatus, int userId, Double total,
			Double subTotal, Integer lineItemCount, Double discountTotal,
			String promoCode, Date createdTime, Date updatedTime,
			Double taxTotal, Set<Invoice> invoices, Set<OrderItem> orderItems) {
		this.orderStatus = orderStatus;
		this.userId = userId;
		this.total = total;
		this.subTotal = subTotal;
		this.lineItemCount = lineItemCount;
		this.discountTotal = discountTotal;
		this.promoCode = promoCode;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.taxTotal = taxTotal;
		this.invoices = invoices;
		this.orderItems = orderItems;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getSubTotal() {
		return this.subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Integer getLineItemCount() {
		return this.lineItemCount;
	}

	public void setLineItemCount(Integer lineItemCount) {
		this.lineItemCount = lineItemCount;
	}

	public Double getDiscountTotal() {
		return this.discountTotal;
	}

	public void setDiscountTotal(Double discountTotal) {
		this.discountTotal = discountTotal;
	}

	public String getPromoCode() {
		return this.promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
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

	public Double getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
