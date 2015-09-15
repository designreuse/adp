package com.interval.service.impl;

import com.interval.dao.impl.OrderStatusDao;
import com.interval.dao.models.OrderStatus;
import com.interval.rest.models.RESTOrderStatus;
import com.interval.service.Service;
import com.interval.transformers.OrderStatusTransformer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/15/2015.
 */
public class OrderStatusService extends BaseService<RESTOrderStatus> {

    private final OrderStatusDao orderStatusDao;

    @Inject
    public OrderStatusService(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public RESTOrderStatus create(RESTOrderStatus restOrderStatus) {
        orderStatusDao.create(OrderStatusTransformer.transformOrderStatus(restOrderStatus));
        return null;
    }

    @Override
    public RESTOrderStatus update(RESTOrderStatus restOrderStatus) {
        orderStatusDao.update(OrderStatusTransformer.transformOrderStatus(restOrderStatus));
        return restOrderStatus;
    }

    @Override
    public RESTOrderStatus get(final String orderStatusId) {
        return OrderStatusTransformer.transformRESTOrderStatus(orderStatusDao.get(orderStatusId));
    }

    @Override
    public List<RESTOrderStatus> getAll() {
        List<RESTOrderStatus> restOrderStatusList = new ArrayList<RESTOrderStatus>();
        List<OrderStatus> orderStatusList = orderStatusDao.getAll();
        for (OrderStatus orderStatus : orderStatusList) {
            restOrderStatusList.add(OrderStatusTransformer.transformRESTOrderStatus(orderStatus));
        }
        return restOrderStatusList;
    }

    @Override
    public void delete(final String orderStatusId) {
        orderStatusDao.delete(orderStatusId);
    }
}
