package com.interval.transformers;

import com.interval.dao.models.User;
import com.interval.rest.models.RESTUser;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by User on 24/9/15.
 */
public class UserTransformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTUser, User> User_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTUser.class, User.class);

    private static final BoundMapperFacade<User, RESTUser> REST_User_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(User.class, RESTUser.class);

    public static User transformUser(final RESTUser input) {
        return User_MAPPER_FACADE.map(input);
    }

    public static RESTUser transformRESTUser(final User input) {
        return REST_User_MAPPER_FACADE.map(input);
    }
}
