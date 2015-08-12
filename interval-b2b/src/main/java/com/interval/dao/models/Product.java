package com.interval.dao.models;

import java.util.Date;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by User on 8/12/2015.
 */
public class Product {
    private int id;
    private String name;
    private String description;
    private byte[] image;
    private Date createdTime;
    private Date updatedTime;
    private int vendorCenterId;
    private int categoryId;
    private Collection<Inventory> inventoriesById;
    private Collection<InventoryTransaction> inventoryTransactionsById;
    private Collection<OrderItem> orderItemsById;
    private Category categoryByCategoryId;
    private VendorCenter vendorCenterByVendorCenterId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getVendorCenterId() {
        return vendorCenterId;
    }

    public void setVendorCenterId(int vendorCenterId) {
        this.vendorCenterId = vendorCenterId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (vendorCenterId != product.vendorCenterId) return false;
        if (categoryId != product.categoryId) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (!Arrays.equals(image, product.image)) return false;
        if (createdTime != null ? !createdTime.equals(product.createdTime) : product.createdTime != null) return false;
        if (updatedTime != null ? !updatedTime.equals(product.updatedTime) : product.updatedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        result = 31 * result + vendorCenterId;
        result = 31 * result + categoryId;
        return result;
    }

    public Collection<Inventory> getInventoriesById() {
        return inventoriesById;
    }

    public void setInventoriesById(Collection<Inventory> inventoriesById) {
        this.inventoriesById = inventoriesById;
    }

    public Collection<InventoryTransaction> getInventoryTransactionsById() {
        return inventoryTransactionsById;
    }

    public void setInventoryTransactionsById(Collection<InventoryTransaction> inventoryTransactionsById) {
        this.inventoryTransactionsById = inventoryTransactionsById;
    }

    public Collection<OrderItem> getOrderItemsById() {
        return orderItemsById;
    }

    public void setOrderItemsById(Collection<OrderItem> orderItemsById) {
        this.orderItemsById = orderItemsById;
    }

    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    public VendorCenter getVendorCenterByVendorCenterId() {
        return vendorCenterByVendorCenterId;
    }

    public void setVendorCenterByVendorCenterId(VendorCenter vendorCenterByVendorCenterId) {
        this.vendorCenterByVendorCenterId = vendorCenterByVendorCenterId;
    }
}
