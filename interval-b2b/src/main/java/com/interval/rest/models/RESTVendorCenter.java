package com.interval.rest.models;

import com.interval.dao.models.Center;
import com.interval.dao.models.Vendor;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTVendorCenter {
    private int id;
    private Center centerByCenterId;
    private Vendor vendorByVendorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
