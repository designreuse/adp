package com.interval.rest.resource;

import com.interval.common.UnMarshaller;
import com.interval.rest.models.RESTCenter;
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
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by User on 8/14/2015.
 */
@Singleton
@Path("center")
@Named
public class CenterResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CenterResource.class);

    private final Service centerService;

    @Inject
    public CenterResource(final Service centerService) {
        this.centerService = centerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCenters() {
        final List<RESTCenter> centerList = (List<RESTCenter>)centerService.getAll();
        return Response.ok().entity(centerList).build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("query") final String query) {
        final List<RESTCenter> centerList = (List<RESTCenter>)centerService.search(query);
        return Response.ok().entity(centerList).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTCenter center = UnMarshaller.unmarshallJSON(RESTCenter.class, request);
            LOGGER.info(center.getName());
            centerService.update(center);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTCenter {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTCenter center = UnMarshaller.unmarshallJSON(RESTCenter.class, request);
            LOGGER.info(center.getName());
            centerService.create(center);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTCenter {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @DELETE
    @Path("{centerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("centerId") final String categoryId) {
        centerService.delete(categoryId);
        return Response.ok().entity(null).build();
    }
}
