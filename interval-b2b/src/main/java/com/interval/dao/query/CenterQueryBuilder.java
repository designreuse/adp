package com.interval.dao.query;

import java.util.List;

/**
 * Created by Muthuraj on 10/12/2015.
 */
public class CenterQueryBuilder {

    public static String search(final String param){
        StringBuilder queryStr = new StringBuilder("from Center c where ");
        queryStr.append("lower(c.name) like lower('").append("%").append(param).append("%").append("')").append(" or ")
                .append("lower(c.address1) like lower('").append("%").append(param).append("%").append("')").append(" or ")
                .append("lower(c.address2) like lower('").append("%").append(param).append("%").append("')").append(" or ")
                .append("lower(c.city) like lower('").append("%").append(param).append("%").append("')").append(" or ")
                .append("lower(c.state) like lower('").append("%").append(param).append("%").append("')").append(" or ")
                .append("lower(c.zip) like lower('").append("%").append(param).append("%").append("')").append(" or ")
                .append("lower(c.country) like lower('").append("%").append(param).append("%").append("')");
        return queryStr.toString();
    }

    public static String updateOrderShowByShow(final List<String> shows){
        StringBuilder query = new StringBuilder("update Order_Detail od set od.show_id = null where od.show_id in (");
        query.append(shows.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append(")");
        return query.toString();
    }

    public static String updateOrderShowByScreen(final List<String> screens){
        StringBuilder query = new StringBuilder("update Order_Detail od set od.show_id = null where od.show_id in (" +
                "select id from show where screen_id in (");
        query.append(screens.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append("))");
        return query.toString();
    }

    public static String deleteShows(final List<String> shows){
        StringBuilder query = new StringBuilder("delete from Show where id in (");
        query.append(shows.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append(")");
        return query.toString();
    }

    public static String deleteShowByScreens(final List<String> screens){
        StringBuilder query = new StringBuilder("delete from Show where screen_id in (");
        query.append(screens.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append(")");
        return query.toString();
    }

    public static String deleteScreens(final List<String> screens){
        StringBuilder query = new StringBuilder("delete from Screen where id in (");
        query.append(screens.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append(")");
        return query.toString();
    }
}
