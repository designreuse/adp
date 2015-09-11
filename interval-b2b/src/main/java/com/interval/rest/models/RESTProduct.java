package com.interval.rest.models;

import java.util.Date;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTProduct {
    private Integer id;
    private String name;
    private String description;
    private byte[] image;
    private Date createdTime;
    private Date updatedTime;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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
}
