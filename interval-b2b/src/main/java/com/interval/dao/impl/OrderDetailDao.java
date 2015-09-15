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

	public OrderDetail getByCenter(final String centerId){
		List<OrderDetail> orderDetailList = null;
		final int id = getId(centerId);
		if(id > 0){
			final String query = "from OrderDetail od where od.show.screen.center.id=" + id;
			orderDetailList = sessionFactory.getCurrentSession().createQuery(query).list();
		}
		return (orderDetailList != null && orderDetailList.size() > 0) ? orderDetailList.get(0) : null;
	}

	public OrderDetail getByUser(final String userId){
		List<OrderDetail> orderDetailList = null;
		final int id = getId(userId);
		if(id > 0){
			final String query = "from OrderDetail od where od.user.id=" + id;
			orderDetailList = sessionFactory.getCurrentSession().createQuery(query).list();
		}
		return (orderDetailList != null && orderDetailList.size() > 0) ? orderDetailList.get(0) : null;
	}
}
