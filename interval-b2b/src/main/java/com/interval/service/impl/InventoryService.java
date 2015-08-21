package com.interval.service.impl;

import com.interval.dao.impl.InventoryDao;
import com.interval.rest.models.RESTInventory;
import com.interval.service.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by User on 8/21/2015.
 */
public class InventoryService implements Service<RESTInventory> {

    private final InventoryDao inventoryDao;

    @Inject
    public InventoryService(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Override
    public RESTInventory create(RESTInventory restInventory) {
        return null;
    }

    @Override
    public RESTInventory update(RESTInventory restInventory) {
        return null;
    }

    @Override
    public RESTInventory get(final String inventoryId) {
        return null;
    }

    @Override
    public List<RESTInventory> getAll() {
        return null;
    }

    @Override
    public void delete(final String inventoryId) {

    }
}
