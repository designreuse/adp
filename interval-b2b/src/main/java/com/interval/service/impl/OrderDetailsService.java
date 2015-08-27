package com.interval.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.interval.dao.impl.OrderDetailDao;
import com.interval.dao.models.*;
import com.interval.rest.models.RESTOrderDetail;
import com.interval.rest.models.RESTOrderItem;
import com.interval.service.Service;

public class OrderDetailsService implements Service<RESTOrderDetail> {
	
	 private final OrderDetailDao orderDetailDao;

	@Inject
	public OrderDetailsService(final OrderDetailDao orderDetailDao){
		this.orderDetailDao = orderDetailDao;
	}
	 
	@Override
	public RESTOrderDetail create(RESTOrderDetail restOrderDetail) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail = toOrderDetail(restOrderDetail);
		orderDetailDao.create(orderDetail);
		return null;
	}

	@Override
	public RESTOrderDetail update(RESTOrderDetail restOrderDetail) {
		return null;
	}

	@Override
	public RESTOrderDetail get(String id) {
		return null;
	}

	@Override
	public List<RESTOrderDetail> getAll() {
		return null;
	}

	@Override
	public void delete(String id) {

	}


	private OrderDetail toOrderDetail(RESTOrderDetail restOrderDetail)
	{
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setLineItemCount(restOrderDetail.getLineItemCount());
		orderDetail.setDiscountTotal(restOrderDetail.getDiscountTotal());
		orderDetail.setPromoCode(restOrderDetail.getPromoCode());
		orderDetail.setSubTotal(restOrderDetail.getSubTotal());
		orderDetail.setTaxTotal(restOrderDetail.getTaxTotal());
		orderDetail.setTotal(restOrderDetail.getTotal());
		orderDetail.setShow(toShow(restOrderDetail.getShowId(),restOrderDetail.getScreenId()));
		orderDetail.setOrderStatus(toOrderStatus(restOrderDetail.getOrderStatus()));
		orderDetail.setUser(toUser(restOrderDetail.getOrderItem().getUserId()));
		orderDetail.setOrderItems(toSetOrderItem(restOrderDetail.getOrderItem(),orderDetail));
		return orderDetail;
	}
	
	private Show toShow(Integer showId, Integer screenId)
	{
		Show show = new Show();
		show.setId(showId);
		show.setScreen(toScreen(screenId));
		return show;
	}
	
	private Screen toScreen(Integer screenId)
	{
		Screen screen = new Screen();
		screen.setId(screenId);
		return screen;
	}
	
	private OrderStatus toOrderStatus(Integer orderStatusId)
	{
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId(orderStatusId);
		return orderStatus;
	}
	
	private User toUser(Integer userId)
	{
		User user = new User();
		user.setId(userId);
		return user;
	}
	
	private Set<OrderItem> toSetOrderItem(RESTOrderItem restOrderItem, OrderDetail orderDetail)
	{
		Set<OrderItem> orderItemSet = new HashSet<OrderItem>(0);
		Product product = new Product();
		product.setId(restOrderItem.getProduct().getId());
		OrderItem orderItem = new OrderItem();		
		orderItem.setQuantity(restOrderItem.getQuantity());
		orderItem.setProduct(product);
		orderItem.setOrderDetail(orderDetail);
		orderItemSet.add(orderItem);
		return orderItemSet;
	}
	

}
