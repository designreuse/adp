package com.interval.dao.impl;

import java.util.List;

/**
 * Created by User on 11/3/2015.
 */
public class DashboardDao extends BaseDao {

    @Override
    public List search(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }
}
