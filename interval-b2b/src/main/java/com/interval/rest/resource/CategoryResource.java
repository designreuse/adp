package com.interval.rest.resource;

import com.interval.dao.models.Category;
import com.interval.service.Service;
import com.interval.service.impl.CategoryService;
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
 * Created by User on 8/12/2015.
 */
@Singleton
@Path("category")
@Named
public class CategoryResource {

    private final Service categoryService;

    @Inject
    public CategoryResource(final Service categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    @Path("/get")
    public Response load() {
        return Response.ok().build();
    }

    @GET
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {

        final List<Category> categoryList = (List<Category>)categoryService.getAll();
        return Response.ok().entity(categoryList).build();
    }


}
