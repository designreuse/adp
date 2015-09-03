package com.interval.rest.models;

import com.interval.dao.models.Center;
import com.interval.dao.models.Vendor;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTVendorCenter {
    private Integer id;
    private Center centerByCenterId;
    private Vendor vendorByVendorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
