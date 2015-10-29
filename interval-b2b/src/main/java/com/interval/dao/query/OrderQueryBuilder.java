package com.interval.dao.query;

import java.util.List;

/**
 * Created by User on 10/12/2015.
 */
public class OrderQueryBuilder {

    public static String getByUser(final String id, final String status){
        final StringBuilder query = new StringBuilder("from OrderDetail od where od.user.id=");
        query.append(id);
        if(status != null){
            query.append(" and od.orderStatus.id=").append(status);
        }
        return query.toString();
    }

    public static String getByCenter(final String id, final String status){
        final StringBuilder query = new StringBuilder("from OrderDetail od where od.show.screen.center.id=");
        query.append(id);
        if(status != null){
            query.append(" and od.orderStatus.id=").append(status);
        }
        return query.toString();
    }

    public static String updateOrderStatus(final String id, final String status){
        StringBuilder query = new StringBuilder("update Order_Detail od set od.status = ").append(status)
                .append(" where od.id = ").append(id);
        return query.toString();
    }
}
