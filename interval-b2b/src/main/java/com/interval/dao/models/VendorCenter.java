package com.interval.dao.models;

import java.util.Collection;

/**
 * Created by User on 8/12/2015.
 */
public class VendorCenter {
    private int id;
    private int centerId;
    private int vendorId;
    private Collection<Product> productsById;
    private Center centerByCenterId;
    private Vendor vendorByVendorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendorCenter that = (VendorCenter) o;

        if (id != that.id) return false;
        if (centerId != that.centerId) return false;
        if (vendorId != that.vendorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + centerId;
        result = 31 * result + vendorId;
        return result;
    }

    public Collection<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<Product> productsById) {
        this.productsById = productsById;
    }

    public Center getCenterByCenterId() {
        return centerByCenterId;
    }

    public void setCenterByCenterId(Center centerByCenterId) {
        this.centerByCenterId = centerByCenterId;
    }

    public Vendor getVendorByVendorId() {
        return vendorByVendorId;
    }

    public void setVendorByVendorId(Vendor vendorByVendorId) {
        this.vendorByVendorId = vendorByVendorId;
    }
}
