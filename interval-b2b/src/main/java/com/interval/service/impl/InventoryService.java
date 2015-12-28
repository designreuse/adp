package com.interval.service.impl;

import com.interval.common.Constants;
import com.interval.dao.impl.InventoryDao;
import com.interval.dao.models.Inventory;
import com.interval.dao.query.InventoryQueryBuilder;
import com.interval.rest.models.RESTInventory;
import com.interval.transformers.InventoryTransformer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        Inventory inventory = InventoryTransformer.transformInventory(restInventory);
        inventory.setCreatedTime(new Date());
        inventoryDao.create(inventory);
        return null;
    }

    @Override
    public RESTInventory update(RESTInventory restInventory) {
        Inventory inventory = InventoryTransformer.transformInventory(restInventory);
        inventory.setUpdatedTime(new Date());
        inventoryDao.update(inventory);
        return restInventory;
    }

    @Override
    public RESTInventory get(final String inventoryId) {
        return InventoryTransformer.transformRESTInventory(inventoryDao.get(inventoryId));
    }

    @Override
    public List<RESTInventory> get(String id, String type, Map<Object, Object> params) {
        List<Inventory> inventories = null;
        List<RESTInventory> restInventories = new ArrayList<RESTInventory>();
        if(type != null && type.equalsIgnoreCase(Constants.CENTER)){
            inventories = inventoryDao.search(InventoryQueryBuilder.getInventoryByCenter(id));
            if(inventories != null){
                for(Inventory inventory : inventories){
                    restInventories.add(InventoryTransformer.transformRESTInventory(inventory));
                }
            }
        }
        return restInventories;
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
