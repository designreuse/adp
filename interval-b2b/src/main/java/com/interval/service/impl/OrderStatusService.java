package com.interval.service.impl;

import com.interval.dao.impl.OrderStatusDao;
import com.interval.dao.models.OrderStatus;
import com.interval.rest.models.RESTOrderStatus;
import com.interval.service.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/15/2015.
 */
public class OrderStatusService implements Service<RESTOrderStatus> {

    private final OrderStatusDao orderStatusDao;

    @Inject
    public OrderStatusService(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public RESTOrderStatus create() {
        return null;
    }

    @Override
    public RESTOrderStatus update(RESTOrderStatus restOrderStatus) {
        orderStatusDao.update(toOrderStatus(restOrderStatus));
        return restOrderStatus;
    }

    @Override
    public RESTOrderStatus get() {
        return null;
    }

    @Override
    public List<RESTOrderStatus> getAll() {
        List<RESTOrderStatus> restOrderStatusList = new ArrayList<RESTOrderStatus>();
        List<OrderStatus> orderStatusList = orderStatusDao.getAll();
        for(OrderStatus orderStatus : orderStatusList){
            restOrderStatusList.add(toRESTOrderStatus(orderStatus));
        }
        return restOrderStatusList;
    }

    @Override
    public void delete() {

    }

    private RESTOrderStatus toRESTOrderStatus(OrderStatus orderStatus){
        RESTOrderStatus restOrderStatus = new RESTOrderStatus();
        restOrderStatus.setId(orderStatus.getId());
        restOrderStatus.setName(orderStatus.getName());
        restOrderStatus.setDescription(orderStatus.getDescription());
        restOrderStatus.setCreatedTime(orderStatus.getCreatedTime());
        restOrderStatus.setUpdatedTime(orderStatus.getUpdatedTime());
        return restOrderStatus;
    }

    private OrderStatus toOrderStatus(RESTOrderStatus restOrderStatus){
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(restOrderStatus.getId());
        orderStatus.setName(restOrderStatus.getName());
        orderStatus.setDescription(restOrderStatus.getDescription());
        orderStatus.setCreatedTime(restOrderStatus.getCreatedTime());
        orderStatus.setUpdatedTime(restOrderStatus.getUpdatedTime());
        return orderStatus;
    }
}
