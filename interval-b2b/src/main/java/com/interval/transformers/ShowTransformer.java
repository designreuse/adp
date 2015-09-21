package com.interval.transformers;

import com.interval.dao.models.Show;
import com.interval.rest.models.RESTShow;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by User on 9/21/2015.
 */
public class ShowTransformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTShow, Show> SHOW_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTShow.class, Show.class);

    private static final BoundMapperFacade<Show, RESTShow> REST_SHOW_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(Show.class, RESTShow.class);

    public static Show transformShow(final RESTShow input) {
        return SHOW_MAPPER_FACADE.map(input);
    }

    public static RESTShow transformRESTShow(final Show input) {
        return REST_SHOW_MAPPER_FACADE.map(input);
    }
}
