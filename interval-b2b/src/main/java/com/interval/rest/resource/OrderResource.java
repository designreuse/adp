package com.interval.rest.resource;


import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.interval.common.Constants;
import com.interval.common.UnMarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interval.rest.models.RESTOrderDetail;
import com.interval.service.Service;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.spi.resource.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                      @QueryParam("type") final String type,
                                      @QueryParam("status") final String status){
        List<RESTOrderDetail> orderDetails;
        Map<Object, Object> params = new HashMap<Object, Object>();
        if(type != null){
            if(status != null){
                params.put(Constants.STATUS, status);
            }
            orderDetails = (List<RESTOrderDetail>)orderDetailsService.get(id, type, params);
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
        } catch (Exception exc) {
            LOGGER.error("exception occurred while converting to RESTOrderDetail {0}", exc);
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
        } catch (Exception exc) {
            LOGGER.error("exception occurred while converting to RESTOrderDetail {0}", exc);
        }
        return Response.ok().entity(orderDetailsResult).build();
    }

    @PUT
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatus(@PathParam("orderId") final String orderId, @QueryParam("status") final String statusId){
        Map<Object, Object> params = new HashMap<Object, Object>();
        try {
            if(statusId != null){
                params.put(Constants.STATUS, statusId);
                orderDetailsService.update(orderId, Constants.STATUS, params);
            }
        } catch (Exception exc) {
            LOGGER.error("exception occurred while converting to RESTOrderDetail {0}", exc);
        }
        return Response.ok().entity(null).build();
    }

    @POST
    @Path("/{orderId}/removeItems")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeItems(@PathParam("orderId") final String orderId){
        orderDetailsService.update(orderId, Constants.REMOVE_ITEMS, null);
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

