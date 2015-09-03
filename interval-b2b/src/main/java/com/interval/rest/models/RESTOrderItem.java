package com.interval.rest.models;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTOrderItem {

    private Integer id;
    private Integer quantity;
    private RESTProduct product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
