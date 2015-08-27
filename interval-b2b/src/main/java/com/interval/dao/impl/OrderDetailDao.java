package com.interval.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;

import com.interval.dao.models.OrderDetail;

import java.util.List;

public class OrderDetailDao extends BaseDao<OrderDetail> {
	
	@Override
	public void create(OrderDetail orderDetail) {
		sessionFactory.getCurrentSession().save(orderDetail);
	}

	@Override
	public void update(OrderDetail orderDetail) {
		sessionFactory.getCurrentSession().saveOrUpdate(orderDetail);
	}

	@Override
	public OrderDetail get(final String orderDetailId) {
		OrderDetail orderDetail = null;
		try{
			orderDetail = (OrderDetail)sessionFactory.getCurrentSession().get(OrderDetail.class, Integer.parseInt(orderDetailId));
		}catch (NumberFormatException exc){

		}
		return orderDetail;
	}

	@Override
	public List<OrderDetail> getAll() {
		final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(OrderDetail.class);
		List<OrderDetail> orderDetailList = criteria.list();
		System.out.println("orderDetailList size : " + orderDetailList.size());
		return orderDetailList;
	}

	@Override
	public void delete(final String orderDetailId) {
		final OrderDetail orderDetail = get(orderDetailId);
		if(orderDetail != null) {
			sessionFactory.getCurrentSession().delete(orderDetail);
		}
	}
}
