package com.interval.rest.resource;

import com.interval.common.UnMarshaller;
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
 * Created by Muthuraj on 9/1/2015.
 */
@Singleton
@Path("dashboard")
@Named
public class DashboardResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardResource.class);

    public DashboardResource() {

    }

    @GET
    @Path("/{centerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboard(@PathParam("centerId") final String centerId) {
        return Response.ok().entity(null).build();
    }
}
