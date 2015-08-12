package com.interval.dao.models;

import java.util.Date;
import java.util.Collection;

/**
 * Created by User on 8/12/2015.
 */
public class Vendor {
    private int id;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String email;
    private Date createdTime;
    private Date updatedTime;
    private Collection<VendorCenter> vendorCentersById;

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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vendor vendor = (Vendor) o;

        if (id != vendor.id) return false;
        if (name != null ? !name.equals(vendor.name) : vendor.name != null) return false;
        if (address1 != null ? !address1.equals(vendor.address1) : vendor.address1 != null) return false;
        if (address2 != null ? !address2.equals(vendor.address2) : vendor.address2 != null) return false;
        if (city != null ? !city.equals(vendor.city) : vendor.city != null) return false;
        if (state != null ? !state.equals(vendor.state) : vendor.state != null) return false;
        if (zip != null ? !zip.equals(vendor.zip) : vendor.zip != null) return false;
        if (country != null ? !country.equals(vendor.country) : vendor.country != null) return false;
        if (phone != null ? !phone.equals(vendor.phone) : vendor.phone != null) return false;
        if (email != null ? !email.equals(vendor.email) : vendor.email != null) return false;
        if (createdTime != null ? !createdTime.equals(vendor.createdTime) : vendor.createdTime != null) return false;
        if (updatedTime != null ? !updatedTime.equals(vendor.updatedTime) : vendor.updatedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        return result;
    }

    public Collection<VendorCenter> getVendorCentersById() {
        return vendorCentersById;
    }

    public void setVendorCentersById(Collection<VendorCenter> vendorCentersById) {
        this.vendorCentersById = vendorCentersById;
    }
}
