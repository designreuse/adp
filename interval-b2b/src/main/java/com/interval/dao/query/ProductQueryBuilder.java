package com.interval.dao.query;

/**
 * Created by User on 10/12/2015.
 */
public class ProductQueryBuilder {

    public static String getProductsByCenter(final String id){
        final String query = "from Product pd where pd.center.id=" + id;
        return query;
    }
}
