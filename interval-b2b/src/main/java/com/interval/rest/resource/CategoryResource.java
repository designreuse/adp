package com.interval.rest.resource;

import com.sun.jersey.spi.resource.Singleton;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by User on 8/12/2015.
 */
@Singleton
@Path("category")
@Named
public class CategoryResource {

    @GET
    @Path("/get")
    public Response load() {
        return Response.ok().build();
    }


}
