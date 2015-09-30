package com.interval.transformers;

import com.interval.dao.models.Center;
import com.interval.rest.models.CenterMenu;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by Muthuraj on 9/30/2015.
 */
public class CommonTranformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<Center, CenterMenu> CENTER_MENU_FACADE =
            MAPPER_FACTORY.getMapperFacade(Center.class, CenterMenu.class);

    public static CenterMenu transformCenterMenu(final Center input) {
        return CENTER_MENU_FACADE.map(input);
    }
}
