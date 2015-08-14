package com.interval.rest.resource;

import com.interval.rest.models.RESTCategory;
import com.interval.rest.models.RESTCenter;
import com.interval.service.Service;
import com.sun.jersey.spi.resource.Singleton;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by User on 8/14/2015.
 */
@Singleton
@Path("center")
@Named
public class CenterResource {

    private final Service centerService;

    @Inject
    public CenterResource(final Service centerService) {
        this.centerService = centerService;
    }

    @GET
    @Path("/centers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        final List<RESTCenter> centerList = (List<RESTCenter>)centerService.getAll();
        return Response.ok().entity(centerList).build();
    }
}
