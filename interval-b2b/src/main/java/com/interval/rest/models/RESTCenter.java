package com.interval.rest.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTCenter {
    private Integer id;
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
    private Set<RESTScreen> screens;
    private Set<RESTVendor> vendors;
    private List<String> deleteScreenList;
    private List<String> deleteShowList;

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

    public Set<RESTScreen> getScreens() {
        return screens;
    }

    public void setScreens(Set<RESTScreen> screens) {
        this.screens = screens;
    }

    public List<String> getDeleteScreenList() {
        return deleteScreenList;
    }

    public void setDeleteScreenList(List<String> deleteScreenList) {
        this.deleteScreenList = deleteScreenList;
    }

    public List<String> getDeleteShowList() {
        return deleteShowList;
    }

    public void setDeleteShowList(List<String> deleteShowList) {
        this.deleteShowList = deleteShowList;
    }

    public Set<RESTVendor> getVendors() {
        return vendors;
    }

    public void setVendors(Set<RESTVendor> vendors) {
        this.vendors = vendors;
    }
}
