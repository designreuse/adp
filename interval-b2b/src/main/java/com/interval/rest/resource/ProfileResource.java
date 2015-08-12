package com.interval.rest.resource;

import com.sun.jersey.spi.resource.Singleton;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by User on 27-07-2015.
 */
@Singleton
@Path("profile")
@Named
public class ProfileResource {

    @GET
    @Path("/get")
    public Response dummyResponse() {
        return Response.ok().build();
    }
}
