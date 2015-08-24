package com.interval.transformers;

import com.interval.dao.models.Category;
import com.interval.rest.models.RESTCategory;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by USER on 24-08-2015.
 */
public class CategoryTransformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTCategory, Category> CATEGORY_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTCategory.class, Category.class);

    private static final BoundMapperFacade<Category, RESTCategory> REST_CATEGORY_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(Category.class, RESTCategory.class);

    public static Category transformCategory(final RESTCategory input) {
        return CATEGORY_MAPPER_FACADE.map(input);
    }

    public static RESTCategory transformRESTCategory(final Category input) {
        return REST_CATEGORY_MAPPER_FACADE.map(input);
    }
}
