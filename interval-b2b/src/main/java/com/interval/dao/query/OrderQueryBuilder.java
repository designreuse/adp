package com.interval.dao.query;

import java.util.List;

/**
 * Created by User on 10/12/2015.
 */
public class OrderQueryBuilder {

    public static String getByUser(final String id){
        final String query = "from OrderDetail od where od.user.id=" + id;
        return query;
    }

    public static String getByCenter(final String id){
        final String query = "from OrderDetail od where od.show.screen.center.id=" + id;
        return query;
    }
}
