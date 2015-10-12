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
        Inventory inventory;
        final int id = getId(inventoryId);
        inventory = (Inventory) sessionFactory.getCurrentSession().get(Inventory.class, id);
        return inventory;
    }

    @Override
    public List<Inventory> getAll() {
        final Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Inventory.class);
        List<Inventory> inventories = criteria.list();
        return inventories;
    }

    @Override
    public void delete(final String inventoryId) {
        final Inventory inventory = get(inventoryId);
        if (inventory != null) {
            sessionFactory.getCurrentSession().delete(inventory);
        }
    }

    @Override
    public List<Inventory> search(String query) {
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }
}
