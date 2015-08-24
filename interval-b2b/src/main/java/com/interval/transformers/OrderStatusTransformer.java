package com.interval.transformers;

import com.interval.dao.models.OrderStatus;
import com.interval.rest.models.RESTOrderStatus;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by USER on 24-08-2015.
 */
public class OrderStatusTransformer {
    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTOrderStatus, OrderStatus> ORDER_STATUS_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTOrderStatus.class, OrderStatus.class);

    private static final BoundMapperFacade<OrderStatus, RESTOrderStatus> REST_ORDER_STATUS_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(OrderStatus.class, RESTOrderStatus.class);

    public static OrderStatus transformOrderStatus(final RESTOrderStatus input) {
        return ORDER_STATUS_MAPPER_FACADE.map(input);
    }

    public static RESTOrderStatus transformRESTOrderStatus(final OrderStatus input) {
        return REST_ORDER_STATUS_MAPPER_FACADE.map(input);
    }

}
