package com.interval.rest.resource;

import com.interval.common.UnMarshaller;
import com.interval.rest.models.RESTProduct;
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
 * Created by USER on 24-08-2015.
 */
@Singleton
@Path("product")
@Named
public class ProductResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

    private final Service productService;

    @Inject
    public ProductResource(final Service productService) {
        this.productService = productService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        final List<RESTProduct> productList = (List<RESTProduct>) productService.getAll();
        return Response.ok().entity(productList).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Context final HttpContext requestContext) {
        String request = requestContext.getRequest().getEntity(String.class);
        try {
            RESTProduct product = UnMarshaller.unmarshallJSON(RESTProduct.class, request);
            productService.update(product);
        } catch (Exception exc) {
            LOGGER.error("exception occurred while converting to RESTProduct {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Context final HttpContext requestContext) {
        String request = requestContext.getRequest().getEntity(String.class);
        try {
            RESTProduct product = UnMarshaller.unmarshallJSON(RESTProduct.class, request);
            LOGGER.info(product.getName());
            productService.create(product);
        } catch (Exception exc) {
            LOGGER.error("exception occurred while converting to RESTProduct {0}", exc);
            return Response.serverError().build();
        }
        return Response.ok().entity(null).build();
    }

    @DELETE
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("productId") final String productId) {
        productService.delete(productId);
        return Response.ok().entity(null).build();
    }
}
