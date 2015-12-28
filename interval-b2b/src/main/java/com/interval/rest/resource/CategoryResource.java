package com.interval.rest.resource;

import com.interval.common.UnMarshaller;
import com.interval.rest.models.RESTCategory;
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
 * Created by User on 8/12/2015.
 */
@Singleton
@Path("category")
@Named
public class CategoryResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryResource.class);

    private final Service categoryService;

    @Inject
    public CategoryResource(final Service categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        final List<RESTCategory> categoryList = (List<RESTCategory>)categoryService.getAll();
        return Response.ok().entity(categoryList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTCategory category = UnMarshaller.unmarshallJSON(RESTCategory.class, request);
            categoryService.create(category);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTCategory {0}", exc);
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
            RESTCategory category = UnMarshaller.unmarshallJSON(RESTCategory.class, request);
            categoryService.update(category);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTCategory {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @DELETE
    @Path("/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("categoryId") final String categoryId) {
        categoryService.delete(categoryId);
        return Response.ok().entity(null).build();
    }

}
