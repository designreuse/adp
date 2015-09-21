package com.interval.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.interval.dao.impl.OrderDetailDao;
import com.interval.dao.impl.OrderItemDao;
import com.interval.dao.impl.ProductDao;
import com.interval.dao.models.*;
import com.interval.rest.models.RESTOrderDetail;
import com.interval.rest.models.RESTOrderItem;
import com.interval.transformers.OrderDetailTransformer;
import com.interval.transformers.OrderItemTransformer;
import com.interval.transformers.ShowTransformer;
import org.springframework.util.CollectionUtils;

public class OrderDetailsService extends BaseService<RESTOrderDetail> {
	
	 private final OrderDetailDao orderDetailDao;
	 private final ProductDao productDao;
	 private final OrderItemDao orderItemDao;

	@Inject
	public OrderDetailsService(final OrderDetailDao orderDetailDao, final ProductDao productDao, final OrderItemDao orderItemDao){
		this.orderDetailDao = orderDetailDao;
		this.productDao = productDao;
		this.orderItemDao = orderItemDao;
	}

	@Override
	public RESTOrderDetail get(String id) {
		return OrderDetailTransformer.transformRESTOrderDetail(orderDetailDao.get(id));
	}

	@Override
	public List<RESTOrderDetail> getAll() {
		return null;
	}

	@Override
	public void delete(String id) {
		orderDetailDao.delete(id);
	}

	@Override
	public List<RESTOrderDetail> get(String id, String type) {
		List<OrderDetail> orderDetailList = null;
		List<RESTOrderDetail> restOrderDetails = new ArrayList<RESTOrderDetail>();
		if(type != null){
			if(type.equalsIgnoreCase("user")){
				orderDetailList = orderDetailDao.getByUser(id);
			}else if(type.equalsIgnoreCase("center")){
				orderDetailList = orderDetailDao.getByCenter(id);
			}
			if(orderDetailList != null){
				for(OrderDetail orderDetail : orderDetailList){
					restOrderDetails.add(OrderDetailTransformer.transformRESTOrderDetail(orderDetail));
				}
			}
		}
		return restOrderDetails;
	}
	 
	@Override
	public RESTOrderDetail create(RESTOrderDetail restOrderDetail) {
		OrderDetail orderDetail = OrderDetailTransformer.transformOrderDetail(restOrderDetail);
		updateLineItemPrice(orderDetail);
		updateTotals(orderDetail);
		orderDetailDao.create(orderDetail);
		RESTOrderDetail updatedRestOrderDetails = OrderDetailTransformer.transformRESTOrderDetail(orderDetail);
		return updatedRestOrderDetails;
	}

	@Override
	public RESTOrderDetail update(RESTOrderDetail restOrderDetail) {
		
		OrderDetail orderDetails = new OrderDetail();
        orderDetails = orderDetailDao.get(restOrderDetail.getId().toString());
        RESTOrderItem restOrderItem = null;
		Iterator iter = restOrderDetail.getOrderItems().iterator();
		restOrderItem = (RESTOrderItem) iter.next();
		OrderItem orderItem = null;
		Product product = new Product();
		boolean isNewItem = false;
		if (null != restOrderItem && null != restOrderItem.getProduct().getId() )
		{
				product.setId(restOrderItem.getProduct().getId());
				product = productDao.get(product.getId().toString());
				orderItem = orderItemDao.getByOrderIdAndProductId(orderDetails, product);
			if (null != orderItem)
			{
				restOrderItem.setId(orderItem.getId());
			}
			else 
			{
				orderItem = OrderItemTransformer.transformOrderItem(restOrderItem);
				isNewItem = true;
			}
			
			Integer deltaQuantity = 0;			
			Integer userEnterQuantity = restOrderItem.getQuantity();
			double deltaPrice = 0;
			 if (null != userEnterQuantity && userEnterQuantity > 0 )
			 {
				 if (null != orderItem && null != orderItem.getQuantity())
			         {
					 	if (!isNewItem)
					 	{
					 		deltaQuantity = userEnterQuantity - orderItem.getQuantity();	
					 	}
					 	else
					 	{
					 		deltaQuantity = orderItem.getQuantity();
					 	}
			         }
				 deltaPrice = deltaQuantity * product.getPrice();
				 orderItem.setQuantity(userEnterQuantity);
				 orderDetails.setLineItemCount(orderDetails.getLineItemCount()+deltaQuantity);
				 orderDetails.setSubTotal(orderDetails.getSubTotal()+deltaPrice);
				 orderDetails.setTotal(orderDetails.getTotal()+deltaPrice);
			}
			 else if (null != userEnterQuantity && userEnterQuantity == 0)
			 {
				 deltaPrice = orderItem.getQuantity() * product.getPrice();
				 orderDetails.setLineItemCount(orderDetails.getLineItemCount()- orderItem.getQuantity());
				 orderDetails.setSubTotal(orderDetails.getSubTotal()- deltaPrice);
				 orderDetails.setTotal(orderDetails.getTotal()- deltaPrice);
				 orderItemDao.delete(orderItem.getId().toString());
				 orderDetails.getOrderItems().remove(orderItem);
			 }
			 orderDetails.getOrderItems().add(orderItem);
			 orderItem.setOrderDetail(orderDetails);
			 orderDetails.getOrderItems().add(orderItem);
			 
		}
		 if (null != restOrderDetail.getShow().getId() && null != restOrderDetail.getSeatNo())
		 {
			  orderDetails.setSeatNo(restOrderDetail.getSeatNo());
			  Show show = ShowTransformer.transformShow(restOrderDetail.getShow());
			  orderDetails.setShow(show);
		 }
		 
	
		 orderDetailDao.update(orderDetails);
		RESTOrderDetail updatedRestOrderDetails = OrderDetailTransformer.transformRESTOrderDetail(orderDetails);
		 return updatedRestOrderDetails;
	}

	private void updateLineItemPrice(OrderDetail orderDetail){
		if(orderDetail != null && !CollectionUtils.isEmpty(orderDetail.getOrderItems())){
			int lineItemCount = 0;
			double lineItemCost = 0.0, orderSubTotal = 0.0;
			for(OrderItem item : orderDetail.getOrderItems()){
				if (null != item){
					Product product = productDao.get(item.getProduct().getId().toString());
					lineItemCount += item.getQuantity();
					lineItemCost = item.getQuantity() * product.getPrice();
					orderSubTotal += lineItemCost;

					item.setOrderDetail(orderDetail);
				}
			}
			orderDetail.setLineItemCount(lineItemCount);
			orderDetail.setSubTotal(orderSubTotal);
		}
	}

	private void updateTotals(OrderDetail orderDetail){
		if(orderDetail != null){
			orderDetail.setTotal(orderDetail.getSubTotal() + (orderDetail.getTaxTotal() != null ? orderDetail.getTaxTotal() : 0.0));
			orderDetail.setDiscountTotal(0.0);
		}
	}

}
