package com.interval.transformers;

import com.interval.dao.models.OrderDetail;
import com.interval.dao.models.OrderItem;
import com.interval.rest.models.RESTOrderDetail;
import com.interval.rest.models.RESTOrderItem;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by User on 9/21/2015.
 */
public class OrderDetailTransformer {

    final static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    private static final BoundMapperFacade<RESTOrderDetail, OrderDetail> ORDER_DETAIL_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(RESTOrderDetail.class, OrderDetail.class);

    private static final BoundMapperFacade<OrderDetail, RESTOrderDetail> REST_ORDER_DETAIL_MAPPER_FACADE =
            MAPPER_FACTORY.getMapperFacade(OrderDetail.class, RESTOrderDetail.class);

    public static OrderDetail transformOrderDetail(final RESTOrderDetail input) {
        return ORDER_DETAIL_MAPPER_FACADE.map(input);
    }

    public static RESTOrderDetail transformRESTOrderDetail(final OrderDetail input) {
        return REST_ORDER_DETAIL_MAPPER_FACADE.map(input);
    }

}
