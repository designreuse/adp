package com.interval.application;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class ApplicationFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger( ApplicationFilter.class.getName());

    public ApplicationFilter(){
        log.info("Application Filter is installed");
    }

    @Override
    public ContainerRequest filter(ContainerRequest requestCtx) {
        String path = requestCtx.getPath();
        log.info( "Filtering request path: " + path );
        // Then check is the service key exists and is valid.
        Authenticator authenticator = Authenticator.getInstance();

      /*  if ( !authenticator.isServiceKeyValid( serviceKey ) ) {
            // Kick anyone without a valid service key
            requestCtx.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );

            return;
        }*/

        // For any other methods besides login, the authToken must be verified
        if ( !path.startsWith( "profile/login" ) ) {
            String username = requestCtx.getHeaderValue("username");
            String access_token = requestCtx.getHeaderValue("access_token");
            // if it isn't valid, just kick them out.
            if ( !authenticator.isAuthTokenValid( username, access_token ) ) {
                //throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
        }
        return requestCtx;
    }

}
