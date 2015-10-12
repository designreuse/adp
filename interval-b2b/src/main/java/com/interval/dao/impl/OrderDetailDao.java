package com.interval.dao.impl;

import com.interval.dao.query.OrderQueryBuilder;
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
		OrderDetail orderDetail;
		final int id = getId(orderDetailId);
		orderDetail = (OrderDetail)sessionFactory.getCurrentSession().get(OrderDetail.class, id);
		return orderDetail;
	}

	@Override
	public List<OrderDetail> getAll() {
		final Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(OrderDetail.class);
		List<OrderDetail> orderDetailList = criteria.list();
		return orderDetailList;
	}

	@Override
	public void delete(final String orderDetailId) {
		final OrderDetail orderDetail = get(orderDetailId);
		if(orderDetail != null) {
			sessionFactory.getCurrentSession().delete(orderDetail);
		}
	}

	@Override
	public List<OrderDetail> search(String query) {
		return sessionFactory.getCurrentSession().createQuery(query).list();
	}

}
