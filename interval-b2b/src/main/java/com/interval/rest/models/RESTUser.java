package com.interval.rest.models;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTUser {
    private Integer id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String password;
    private Date createdTime;
    private Date updatedTime;
    private RESTRole role;
    private String token;
    private RESTVendor vendor;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RESTRole getRole() {
        return role;
    }

    public void setRole(RESTRole role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RESTVendor getVendor() {
        return vendor;
    }

    public void setVendor(RESTVendor vendor) {
        this.vendor = vendor;
    }
}
