package com.interval.rest.resource;

import com.interval.common.UnMarshaller;
import com.interval.rest.models.RESTUser;
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
@Path("profile")
@Named
public class ProfileResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileResource.class);
    private final Service profileService;

    @Inject
    public ProfileResource(Service profileService) {
        this.profileService = profileService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfiles() {
        final List<RESTUser> restUserList = profileService.getAll();
        return Response.ok(restUserList).build();
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileForId(@PathParam("userId") final String userID) {
        final RESTUser restUser = (RESTUser) profileService.get(userID);
        return Response.ok(restUser).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfile(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTUser restUser = UnMarshaller.unmarshallJSON(RESTUser.class, request);
            profileService.update(restUser);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTUser {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProfile(@Context final HttpContext requestContext){
        String request = requestContext.getRequest().getEntity(String.class);
        try{
            RESTUser restUser = UnMarshaller.unmarshallJSON(RESTUser.class, request);
            profileService.create(restUser);
        }catch (Exception exc){
            LOGGER.error("exception occurred while converting to RESTUser {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @DELETE
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfile(@PathParam("userId") final String userID){
        profileService.delete(userID);
        return Response.ok(null).build();
    }
}
