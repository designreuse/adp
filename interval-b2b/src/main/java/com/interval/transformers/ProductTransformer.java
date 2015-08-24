package com.interval.transformers;

import com.interval.dao.models.Product;
import com.interval.rest.models.RESTProduct;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by USER on 24-08-2015.
 */
public class ProductTransformer {
    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTProduct, Product> PRODUCT_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTProduct.class, Product.class);

    private static final BoundMapperFacade<Product, RESTProduct> REST_PRODUCT_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(Product.class, RESTProduct.class);

    public static Product transformProduct(final RESTProduct input) {
        return PRODUCT_MAPPER_FACADE.map(input);
    }

    public static RESTProduct transformRESTProduct(final Product input) {
        return REST_PRODUCT_MAPPER_FACADE.map(input);
    }
}
