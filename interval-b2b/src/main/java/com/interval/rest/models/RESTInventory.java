package com.interval.rest.models;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTInventory {
    private int id;
    private Integer availableQuantity;
    private Integer safetyStock;
    private Boolean availability;
    private RESTProduct product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getSafetyStock() {
        return safetyStock;
    }

    public void setSafetyStock(Integer safetyStock) {
        this.safetyStock = safetyStock;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public RESTProduct getProduct() {
        return product;
    }

    public void setProduct(RESTProduct product) {
        this.product = product;
    }
}
