package com.interval.rest.models;

import com.interval.dao.models.TransactionType;

import java.util.Date;

/**
 * Created by USER on 11-08-2015.
 */
public class RESTTransaction {
    private Integer id;
    private Date createdTime;
    private Date updatedTime;
    private TransactionType transactionTypeByTransactionTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
