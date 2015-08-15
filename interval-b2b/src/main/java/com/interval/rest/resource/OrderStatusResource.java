package com.interval.rest.resource;

import com.interval.common.UnMarshaller;
import com.interval.rest.models.RESTOrderStatus;
import com.interval.service.Service;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.spi.resource.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by User on 8/15/2015.
 */
@Singleton
@Path("orderStatus")
@Named
public class OrderStatusResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusResource.class);

    private final Service orderStatusService;

    @Inject
    public OrderStatusResource(final Service orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderStatus() {
        final List<RESTOrderStatus> centerList = (List<RESTOrderStatus>)orderStatusService.getAll();
        return Response.ok().entity(centerList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTOrderStatus restOrderStatus = UnMarshaller.unmarshallJSON(RESTOrderStatus.class, request);
            LOGGER.info(restOrderStatus.getName());
            orderStatusService.update(restOrderStatus);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTOrderStatus {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }
}
