package com.interval.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.interval.dao.models.OrderDetail;
import com.interval.dao.models.OrderItem;
import com.interval.dao.models.Product;

public class OrderItemDao extends BaseDao<OrderItem> {
	
	
	public OrderItem getByOrderIdAndProductId(final OrderDetail orderDetails, final Product product)
	{
		final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(OrderItem.class);
		Criterion orderItemFilter = Restrictions.and(Restrictions.eq("orderDetail", orderDetails), Restrictions.eq("product", product));
		criteria.add(orderItemFilter);
		OrderItem orderItem = (OrderItem) criteria.uniqueResult();		
		return orderItem;
	}
	
	@Override
    public OrderItem get(final String orderItemId) {
        OrderItem orderItem;
        final int id = getId(orderItemId);
        orderItem = (OrderItem) sessionFactory.getCurrentSession().get(OrderItem.class, id);
        return orderItem;
    }
	
	 @Override
	 public void delete(final String orderItemId) {
	        final OrderItem orderItem = get(orderItemId);
	        if (orderItem != null) {
	        	orderItem.getOrderDetail().getOrderItems().remove(orderItem);
	            sessionFactory.getCurrentSession().delete(orderItem);
	        }
	    }   
}
