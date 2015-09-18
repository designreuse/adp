package com.interval.rest.models;

import java.util.Date;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTProduct {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private Date createdTime;
    private Date updatedTime;
    private boolean active;
    private double price;
    private RESTCategory category;
    private RESTInventory inventory;
    private RESTCenter center;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public RESTCategory getCategory() {
        return category;
    }

    public void setCategory(RESTCategory category) {
        this.category = category;
    }

    public RESTInventory getInventory() {
        return inventory;
    }

    public void setInventory(RESTInventory inventory) {
        this.inventory = inventory;
    }

    public RESTCenter getCenter() {
        return center;
    }

    public void setCenter(final RESTCenter center) {
        this.center = center;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
