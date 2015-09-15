package com.interval.service.impl;

import com.interval.dao.impl.InventoryDao;
import com.interval.dao.models.Inventory;
import com.interval.rest.models.RESTInventory;
import com.interval.service.Service;
import com.interval.transformers.InventoryTransformer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/21/2015.
 */
public class InventoryService extends BaseService<RESTInventory> {

    private final InventoryDao inventoryDao;

    @Inject
    public InventoryService(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Override
    public RESTInventory create(RESTInventory restInventory) {
        inventoryDao.create(InventoryTransformer.transformInventory(restInventory));
        return null;
    }

    @Override
    public RESTInventory update(RESTInventory restInventory) {
        inventoryDao.update(InventoryTransformer.transformInventory(restInventory));
        return restInventory;
    }

    @Override
    public RESTInventory get(final String inventoryId) {
        return InventoryTransformer.transformRESTInventory(inventoryDao.get(inventoryId));
    }

    @Override
    public List<RESTInventory> getAll() {
        List<RESTInventory> inventoryList = new ArrayList<RESTInventory>();
        List<Inventory> inventories = inventoryDao.getAll();
        for (Inventory inventory : inventories) {
            inventoryList.add(InventoryTransformer.transformRESTInventory(inventory));
        }
        return inventoryList;
    }

    @Override
    public void delete(final String inventoryId) {
        inventoryDao.delete(inventoryId);
    }
}
