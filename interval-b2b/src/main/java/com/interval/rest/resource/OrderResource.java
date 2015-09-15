package com.interval.rest.resource;


import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.interval.common.UnMarshaller;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByFilter(@PathParam("id") final String id,
                                      @QueryParam("type") final String type){
        orderDetailsService.get(id, type);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);

        return Response.ok().entity(null).build();
    }

    @PUT
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatus(@PathParam("orderId") final String orderId,
                                 @Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);

        return Response.ok().entity(null).build();
    }

    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(@Context final HttpContext requestContext) {
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
            orderDetails.setShowId(14);
            orderDetails.setScreenId(5);
            orderDetails.setOrderStatus(3);

            RESTOrderItem orderItem = new RESTOrderItem();
            RESTProduct product = new RESTProduct();
            product.setId(3);
            orderItem.setQuantity(5);
            orderItem.setProduct(product);
            orderDetails.setOrderItem(orderItem);

            orderDetailsService.create(orderDetails);


            temp = "Product ID" + orderDetails.getLineItemCount();
            temp = temp.concat("Quantity : " + orderDetails.getDiscountTotal().toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Response.ok().entity(temp).build();
    }

}

