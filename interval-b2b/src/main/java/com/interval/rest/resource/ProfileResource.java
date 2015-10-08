package com.interval.rest.resource;

import com.interval.application.Authenticator;
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
import javax.ws.rs.core.*;
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
    private final Authenticator authenticator;

    @Inject
    public ProfileResource(Service profileService, Authenticator authenticator) {
        this.profileService = profileService;
        this.authenticator = authenticator;
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

    @POST
    @Path( "login" )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces( MediaType.TEXT_PLAIN)
    public String login(@Context final HttpContext requestContext) {
        String request = requestContext.getRequest().getEntity(String.class);
        String auth_token;
        try {
            RESTUser restUser = UnMarshaller.unmarshallJSON(RESTUser.class, request);
            auth_token = authenticator.login(restUser.getEmail(), restUser.getPassword());
        } catch (Exception exc) {
            LOGGER.error("exception occurred while logging in", exc);
            return Response.Status.INTERNAL_SERVER_ERROR.toString();
        }
        return auth_token;
    }

    @POST
    @Path( "logout" )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces( MediaType.APPLICATION_JSON )
    public Response logout(@Context final HttpHeaders httpHeaders) {

        try {
            if(httpHeaders.getRequestHeader("auth_token") != null){
                String auth_token = httpHeaders.getRequestHeader("auth_token").get(0);
                authenticator.logout(auth_token);
                return getNoCacheResponseBuilder( Response.Status.NO_CONTENT).build();
            }
        } catch (Exception exc) {
            LOGGER.error("exception occurred while logging out", exc);
            return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return getNoCacheResponseBuilder(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private Response.ResponseBuilder getNoCacheResponseBuilder( Response.Status status ) {
        CacheControl cc = new CacheControl();
        cc.setNoCache( true );
        cc.setMaxAge( -1 );
        cc.setMustRevalidate( true );
        return Response.status( status ).cacheControl( cc );
    }

}
