package com.interval.dao.impl;

import com.interval.dao.models.Category;
import com.interval.dao.models.OrderStatus;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by User on 8/15/2015.
 */
public class OrderStatusDao extends BaseDao<OrderStatus> {

    @Override
    public void create(OrderStatus orderStatus) {
        sessionFactory.getCurrentSession().saveOrUpdate(orderStatus);
    }

    @Override
    public void update(OrderStatus orderStatus) {
        sessionFactory.getCurrentSession().saveOrUpdate(orderStatus);
    }

    @Override
    public OrderStatus get(final String orderStatusId) {
        OrderStatus orderStatus;
        final int id = getId(orderStatusId);
        orderStatus = (OrderStatus)sessionFactory.getCurrentSession().get(OrderStatus.class, id);
        return orderStatus;
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
    public void delete(final String orderStatusId) {
        final OrderStatus orderStatus = get(orderStatusId);
        if(orderStatus != null) {
            sessionFactory.getCurrentSession().delete(orderStatus);
        }
    }
}
