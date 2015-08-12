package com.interval.dao.models;

import java.util.Date;
import java.util.Collection;

/**
 * Created by User on 8/12/2015.
 */
public class OrderDetail {
    private int id;
    private int userId;
    private double total;
    private double subTotal;
    private int lineItemCount;
    private double discountTotal;
    private String promoCode;
    private Date createdTime;
    private Date updatedTime;
    private double taxTotal;
    private int status;
    private Collection<Invoice> invoicesById;
    private OrderStatus orderStatusByStatus;
    private Collection<OrderItem> orderItemsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (status != that.status) return false;
        if (total != that.total) return false;
        if (subTotal != that.subTotal) return false;
        if (lineItemCount != that.lineItemCount)
            return false;
        if (discountTotal != that.discountTotal)
            return false;
        if (promoCode != null ? !promoCode.equals(that.promoCode) : that.promoCode != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;
        if (taxTotal != that.taxTotal) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;

        return result;
    }

    public Collection<Invoice> getInvoicesById() {
        return invoicesById;
    }

    public void setInvoicesById(Collection<Invoice> invoicesById) {
        this.invoicesById = invoicesById;
    }

    public OrderStatus getOrderStatusByStatus() {
        return orderStatusByStatus;
    }

    public void setOrderStatusByStatus(OrderStatus orderStatusByStatus) {
        this.orderStatusByStatus = orderStatusByStatus;
    }

    public Collection<OrderItem> getOrderItemsById() {
        return orderItemsById;
    }

    public void setOrderItemsById(Collection<OrderItem> orderItemsById) {
        this.orderItemsById = orderItemsById;
    }
}
