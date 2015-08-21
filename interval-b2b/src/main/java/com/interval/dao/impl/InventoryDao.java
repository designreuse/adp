package com.interval.dao.impl;

import com.interval.dao.models.Inventory;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by User on 8/21/2015.
 */
public class InventoryDao extends BaseDao<Inventory> {

    @Override
    public void create(Inventory inventory) {
        sessionFactory.getCurrentSession().save(inventory);
    }

    @Override
    public void update(Inventory inventory) {
        sessionFactory.getCurrentSession().saveOrUpdate(inventory);
    }

    @Override
    public Inventory get(final String inventoryId) {
        Inventory inventory = null;
        try{
            inventory = (Inventory)sessionFactory.getCurrentSession().get(Inventory.class, Integer.parseInt(inventoryId));
        }catch (NumberFormatException exc){

        }
        return inventory;
    }

    @Override
    public List<Inventory> getAll() {
        final Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Inventory.class);
        List<Inventory> inventories = criteria.list();
        System.out.println("inventories size : " + inventories.size());
        return inventories;
    }

    @Override
    public void delete(final String inventoryId) {
        final Inventory inventory = get(inventoryId);
        if(inventory != null) {
            sessionFactory.getCurrentSession().delete(inventory);
        }
    }
}
