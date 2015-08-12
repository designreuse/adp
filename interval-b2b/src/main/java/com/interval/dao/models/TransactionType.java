package com.interval.dao.models;

import java.util.Collection;

/**
 * Created by User on 8/12/2015.
 */
public class TransactionType {
    private int id;
    private String name;
    private Collection<InventoryTransaction> inventoryTransactionsById;
    private Collection<Transaction> transactionsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionType that = (TransactionType) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<InventoryTransaction> getInventoryTransactionsById() {
        return inventoryTransactionsById;
    }

    public void setInventoryTransactionsById(Collection<InventoryTransaction> inventoryTransactionsById) {
        this.inventoryTransactionsById = inventoryTransactionsById;
    }

    public Collection<Transaction> getTransactionsById() {
        return transactionsById;
    }

    public void setTransactionsById(Collection<Transaction> transactionsById) {
        this.transactionsById = transactionsById;
    }
}
