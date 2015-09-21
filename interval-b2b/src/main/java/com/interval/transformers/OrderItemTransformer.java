package com.interval.transformers;

import com.interval.dao.models.OrderItem;
import com.interval.rest.models.RESTOrderItem;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by User on 9/21/2015.
 */
public class OrderItemTransformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTOrderItem, OrderItem> ORDER_ITEM_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTOrderItem.class, OrderItem.class);

    private static final BoundMapperFacade<OrderItem, RESTOrderItem> REST_ORDER_ITEM_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(OrderItem.class, RESTOrderItem.class);

    public static OrderItem transformOrderItem(final RESTOrderItem input) {
        return ORDER_ITEM_MAPPER_FACADE.map(input);
    }

    public static RESTOrderItem transformRESTOrderItem(final OrderItem input) {
        return REST_ORDER_ITEM_MAPPER_FACADE.map(input);
    }
}
