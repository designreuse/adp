package com.interval.rest.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTOrderDetail {
    private Integer id;
    private Double total;
    private Double subTotal;
    private Integer lineItemCount;
    private Double discountTotal;
    private String promoCode;
    private Date createdTime;
    private Date updatedTime;
    private Double taxTotal;
    private String seatNo;
    private RESTShow show;
    private RESTUser user;
    private Set<RESTInvoice> invoices = new HashSet<RESTInvoice>(0);
	private Set<RESTOrderItem> orderItems = new HashSet<RESTOrderItem>(0);
    private RESTOrderStatus orderStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getLineItemCount() {
        return lineItemCount;
    }

    public void setLineItemCount(Integer lineItemCount) {
        this.lineItemCount = lineItemCount;
    }

    public Double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(Double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Double getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(Double taxTotal) {
        this.taxTotal = taxTotal;
    }

	public RESTOrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(RESTOrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Set<RESTInvoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<RESTInvoice> invoices) {
		this.invoices = invoices;
	}

	public Set<RESTOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<RESTOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public RESTUser getUser() {
		return user;
	}

	public void setUser(RESTUser user) {
		this.user = user;
	}

	public RESTShow getShow() {
		return show;
	}

	public void setShow(RESTShow show) {
		this.show = show;
	}

}
