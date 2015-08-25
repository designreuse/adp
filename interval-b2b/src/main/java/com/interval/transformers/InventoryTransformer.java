package com.interval.transformers;

import com.interval.dao.models.Inventory;
import com.interval.rest.models.RESTInventory;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by USER on 25-08-2015.
 */
public class InventoryTransformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTInventory, Inventory> INVENTORY_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTInventory.class, Inventory.class);

    private static final BoundMapperFacade<Inventory, RESTInventory> REST_INVENTORY_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(Inventory.class, RESTInventory.class);

    public static Inventory transformInventory(final RESTInventory input) {
        return INVENTORY_MAPPER_FACADE.map(input);
    }

    public static RESTInventory transformRESTInventory(final Inventory input) {
        return REST_INVENTORY_MAPPER_FACADE.map(input);
    }
}
