package com.interval.dao.query;

/**
 * Created by User on 10/12/2015.
 */
public class InventoryQueryBuilder {

    public static String getInventoryByCenter(final String id){
        final String query = "from Inventory iv where iv.product.center.id=" + id;
        return query;
    }
}
