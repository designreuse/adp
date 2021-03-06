package com.interval.rest.resource;

import com.interval.common.Constants;
import com.interval.service.Service;
import com.sun.jersey.spi.resource.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by User on 9/1/2015.
 */
@Singleton
@Path("dashboard")
@Named
public class DashboardResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardResource.class);

    private final Service dashBoardService;

    @Inject
    public DashboardResource(final Service dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByCenter() {
        List data;
        data = dashBoardService.get(null, Constants.PRODUCT_COUNT, null);
        return Response.ok().entity(data).build();
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByCenter() {
        List data;
        data = dashBoardService.get(null, Constants.ORDER_COUNT, null);
        return Response.ok().entity(data).build();
    }
}
