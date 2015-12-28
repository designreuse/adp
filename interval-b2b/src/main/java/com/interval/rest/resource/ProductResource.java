package com.interval.rest.resource;

import com.interval.common.CommonUtil;
import com.interval.common.UnMarshaller;
import com.interval.rest.models.RESTProduct;
import com.interval.service.Service;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.spi.resource.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 24-08-2015.
 */
@Singleton
@Path("product")
@Named
public class ProductResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

    private static final String IMAGE_UPLOAD_DIR = "E:\\User\\app\\images\\";

    private final Service productService;

    @Inject
    public ProductResource(final Service productService) {
        this.productService = productService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByFilter(@PathParam("id") final String id, @QueryParam("type") final String type){
        List<RESTProduct> products;
        if(type != null){
            products = (List<RESTProduct>)productService.get(id, type, null);
        }else{
            RESTProduct product = (RESTProduct)productService.get(id);
            products = new ArrayList<RESTProduct>();
            products.add(product);
        }
        return Response.ok(products).build();
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

    @POST
    @Path("{productId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadImage(@PathParam("productId") final String productId, FormDataMultiPart form){
        FormDataBodyPart filePart = form.getField("imageFile");
        ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
        String imageFileName = (String)form.getField("imageFileName").getValue();
        String imageFileType = (String)form.getField("imageFileType").getValue();
        String request = (String)form.getField("product").getValue();
        String fileName = CommonUtil.getFileName(productId, imageFileType);
        try {
            RESTProduct product = UnMarshaller.unmarshallJSON(RESTProduct.class, request);
            InputStream fileInputStream = filePart.getValueAs(InputStream.class);
            CommonUtil.writeToFile(fileInputStream, IMAGE_UPLOAD_DIR, fileName);
            product.setImage(IMAGE_UPLOAD_DIR + fileName);
            productService.update(product);
        } catch (Exception exc) {
            LOGGER.error("exception occurred while converting to RESTProduct {0}", exc);
            return Response.serverError().build();
        }

        return Response.ok().entity(null).build();
    }
}
