package com.interval.transformers;

import com.interval.dao.models.Center;
import com.interval.rest.models.RESTCenter;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by USER on 24-08-2015.
 */
public class CenterTransformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTCenter, Center> CENTER_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTCenter.class, Center.class);

    private static final BoundMapperFacade<Center, RESTCenter> REST_CENTER_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(Center.class, RESTCenter.class);

    public static Center transformCenter(final RESTCenter input) {
        return CENTER_MAPPER_FACADE.map(input);
    }

    public static RESTCenter transformRESTCenter(final Center input) {
        return REST_CENTER_MAPPER_FACADE.map(input);
    }

}

