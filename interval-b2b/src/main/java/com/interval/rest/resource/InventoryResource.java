package com.interval.rest.resource;

import com.interval.common.UnMarshaller;
import com.interval.rest.models.RESTInventory;
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
 * Created by User on 27-07-2015.
 */
@Singleton
@Path("inventory")
@Named
public class InventoryResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryResource.class);

    private Service inventoryService;

    @Inject
    public InventoryResource(final Service inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryList() {
        final List<RESTInventory> inventoryList = (List<RESTInventory>)inventoryService.getAll();
        return Response.ok().entity(inventoryList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTInventory restInventory = UnMarshaller.unmarshallJSON(RESTInventory.class, request);
            LOGGER.info("inventory id ",restInventory.getId());
            inventoryService.create(restInventory);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTInventory {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTInventory restInventory = UnMarshaller.unmarshallJSON(RESTInventory.class, request);
            LOGGER.info("inventory id ",restInventory.getId());
            inventoryService.update(restInventory);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTInventory {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @DELETE
    @Path("/{inventoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("inventoryId") final String inventoryId) {
        inventoryService.delete(inventoryId);
        return Response.ok().entity(null).build();
    }

}
