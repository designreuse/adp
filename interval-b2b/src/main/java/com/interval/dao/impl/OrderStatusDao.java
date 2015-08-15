package com.interval.dao.impl;

import com.interval.dao.models.OrderStatus;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by User on 8/15/2015.
 */
public class OrderStatusDao extends BaseDao<OrderStatus> {

    @Override
    public void create() {

    }

    @Override
    public void update(OrderStatus orderStatus) {
        sessionFactory.getCurrentSession().saveOrUpdate(orderStatus);
    }

    @Override
    public void get() {

    }

    @Override
    public List<OrderStatus> getAll() {
        final Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(OrderStatus.class);
        List<OrderStatus> orderStatusList = criteria.list();
        System.out.println("orderStatusList size : " + orderStatusList.size());
        return orderStatusList;
    }

    @Override
    public void delete() {

    }
}
