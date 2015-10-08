package com.interval.rest.resource;

import com.interval.rest.models.RESTCenter;
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
 * Created by User on 9/30/2015.
 */
@Singleton
@Path("menu")
@Named
public class MenuResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuResource.class);

    private final Service commonService;

    @Inject
    public MenuResource(final Service commonService) {
        this.commonService = commonService;
    }

    @GET
    @Path("{centerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenuByCenter(@PathParam("centerId") final String centerId,
                                    @QueryParam("type") final String type) {
        final List<Object> menu = (List<Object>)commonService.get(centerId, type);
        return Response.ok().entity(menu.get(0)).build();
    }
}
