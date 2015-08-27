package com.interval.rest.resource;


import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.interval.rest.models.RESTProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interval.common.Constants;
import com.interval.rest.models.RESTOrderDetail;
import com.interval.rest.models.RESTOrderItem;
import com.interval.service.Service;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.spi.resource.Singleton;

/**
 * Created by User on 27-07-2015.
 */
@Singleton
@Path("order")
@Named
public class OrderResource {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(OrderResource.class);
	
	 private final Service orderDetailsService;
	 
	 @Inject
	    public OrderResource(final Service orderDetailsService) {
	        this.orderDetailsService = orderDetailsService;
	    }

    @GET
    @Path("/get")
    public Response dummyResponse() {
        return Response.ok().build();
    }
    
    @GET
    @Path ("/createOrder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(@Context final HttpContext requestContext)
    {
    	String request = requestContext.getRequest().getEntity(String.class);
    	String temp = Constants.EMPTY_STRING;
    	try {
			//RESTOrderItem orderItems = UnMarshaller.unmarshallJSON(RESTOrderDetail.class, request);
    		RESTOrderDetail orderDetails = new RESTOrderDetail();
			orderDetails.setLineItemCount(5);
			orderDetails.setDiscountTotal(0.00);
			orderDetails.setPromoCode(Constants.EMPTY_STRING);
			orderDetails.setSubTotal(50.00);
			orderDetails.setTaxTotal(8.12);
			orderDetails.setTotal(58.12);
			orderDetails.setShowId(0);
			orderDetails.setScreenId(0);
			orderDetails.setOrderStatus(1);
			
			RESTOrderItem orderItem = new RESTOrderItem();
			RESTProduct product = new RESTProduct();
			product.setId(10);
			orderItem.setQuantity(5);
			orderItem.setUserId(0);
			orderItem.setProduct(product);
			orderDetails.setOrderItem(orderItem);
			
			orderDetailsService.create(orderDetails);
			
			
			
			temp = "Product ID"+orderDetails.getLineItemCount();
			temp = temp.concat("Quantity : "+orderDetails.getDiscountTotal().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return Response.ok().entity(temp).build();
    }

}

