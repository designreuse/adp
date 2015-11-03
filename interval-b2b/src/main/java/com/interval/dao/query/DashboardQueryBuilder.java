package com.interval.dao.query;

/**
 * Created by User on 11/3/2015.
 */
public class DashboardQueryBuilder {

    public static String productCountByCenter(){
        StringBuilder queryStr = new StringBuilder("select center_id as id, cn.name as name, count(*) as productCount from " +
                "product pd, center cn where pd.center_id = cn.id group by center_id, cn.name");
        return queryStr.toString();
    }

    public static String orderCountByCenter(){
        StringBuilder queryStr = new StringBuilder("select cn.id as id, cn.name as name, count(*) as orderCount from " +
                "order_detail od,center cn, show sh, screen sn where od.show_id = sh.id and " +
                "sh.screen_id = sn.id and sn.center_id = cn.id group by show_id, cn.name, cn.id");
        return queryStr.toString();
    }
}
