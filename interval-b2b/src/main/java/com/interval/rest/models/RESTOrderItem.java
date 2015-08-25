package com.interval.rest.models;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTOrderItem {
    private int userId;
    private Integer quantity;
    private RESTProduct product;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public RESTProduct getProduct() {
        return product;
    }

    public void setProduct(RESTProduct product) {
        this.product = product;
    }
}
