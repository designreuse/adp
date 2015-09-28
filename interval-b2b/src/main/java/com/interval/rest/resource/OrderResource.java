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

import java.util.ArrayList;
import java.util.List;

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
        List<RESTOrderDetail> orderDetails;
        if(type != null){
            orderDetails = (List<RESTOrderDetail>)orderDetailsService.get(id, type);
        }else{
            RESTOrderDetail orderDetail = (RESTOrderDetail)orderDetailsService.get(id);
            orderDetails = new ArrayList<RESTOrderDetail>();
            orderDetails.add(orderDetail);
        }
        return Response.ok(orderDetails).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context final HttpContext requestContext) {
        String request = requestContext.getRequest().getEntity(String.class);
        RESTOrderDetail updatedRestOrderDetails = null;
        try {
            RESTOrderDetail restOrderDetails = UnMarshaller.unmarshallJSON(RESTOrderDetail.class, request);
            updatedRestOrderDetails = (RESTOrderDetail) orderDetailsService.create(restOrderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity(updatedRestOrderDetails).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        RESTOrderDetail orderDetailsResult = new RESTOrderDetail();
        try {
        	RESTOrderDetail orderDetails = UnMarshaller.unmarshallJSON(RESTOrderDetail.class, request);
        	orderDetailsResult = (RESTOrderDetail) orderDetailsService.update(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity(orderDetailsResult).build();
    }

    @POST
    @Path("/{orderId}/removeItems")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeItems(@PathParam("orderId") final String orderId){
        orderDetailsService.update(orderId, "remove items");
        return Response.ok().entity(null).build();
    }
    
    @DELETE
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("orderId") final String orderId){
        orderDetailsService.delete(orderId);
        return Response.ok().entity(null).build();
    }

}

