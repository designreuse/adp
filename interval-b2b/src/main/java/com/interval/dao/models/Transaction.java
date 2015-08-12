package com.interval.dao.models;

import java.util.Date;

/**
 * Created by User on 8/12/2015.
 */
public class Transaction {
    private int id;
    private int transactionTypeId;
    private Date createdTime;
    private Date updatedTime;
    private TransactionType transactionTypeByTransactionTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
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

        Transaction that = (Transaction) o;

        if (id != that.id) return false;
        if (transactionTypeId != that.transactionTypeId) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + transactionTypeId;
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        return result;
    }

    public TransactionType getTransactionTypeByTransactionTypeId() {
        return transactionTypeByTransactionTypeId;
    }

    public void setTransactionTypeByTransactionTypeId(TransactionType transactionTypeByTransactionTypeId) {
        this.transactionTypeByTransactionTypeId = transactionTypeByTransactionTypeId;
    }
}
