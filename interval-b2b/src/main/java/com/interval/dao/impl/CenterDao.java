package com.interval.dao.impl;

import com.interval.dao.models.Center;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by User on 8/14/2015.
 */
public class CenterDao extends BaseDao<Center> {

    @Override
    public void create(Center center) {
        sessionFactory.getCurrentSession().save(center);
    }

    @Override
    public void update(Center center) {
        sessionFactory.getCurrentSession().saveOrUpdate(center);
    }

    @Override
    public Center get(final String centerId) {
        Center center;
        final int id = getId(centerId);
        center = (Center)sessionFactory.getCurrentSession().get(Center.class, id);
        return center;
    }

    @Override
    public List<Center> getAll() {
        final Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Center.class);
        return criteria.list();
    }

    @Override
    public void delete(final String centerId) {
        final Center center = get(centerId);
        if(center != null) {
            sessionFactory.getCurrentSession().delete(center);
        }
    }

    public void deleteScreens(final int centerId, List<String> screens){
        StringBuilder showQuery = new StringBuilder("delete from Show where screen_id in (");
        showQuery.append(screens.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append(")");
        sessionFactory.getCurrentSession().createQuery(showQuery.toString()).executeUpdate();

        StringBuilder screenQuery = new StringBuilder("delete from Screen where id in (");
        screenQuery.append(screens.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append(")");
        sessionFactory.getCurrentSession().createQuery(screenQuery.toString()).executeUpdate();
    }

    public void deleteShows(final int centerId, List<String> shows){
        StringBuilder query = new StringBuilder("delete from Show where id in (");
        query.append(shows.toString().replaceAll("\\[", "").replaceAll("\\]", "")).append(")");
        sessionFactory.getCurrentSession().createQuery(query.toString()).executeUpdate();
    }

    @Override
    public List<Center> search(String query) {
        StringBuilder queryStr = new StringBuilder("from Center c where ");
        queryStr.append("lower(c.name) like lower('").append("%").append(query).append("%").append("')").append(" or ")
                .append("lower(c.address1) like lower('").append("%").append(query).append("%").append("')").append(" or ")
                .append("lower(c.address2) like lower('").append("%").append(query).append("%").append("')").append(" or ")
                .append("lower(c.city) like lower('").append("%").append(query).append("%").append("')").append(" or ")
                .append("lower(c.state) like lower('").append("%").append(query).append("%").append("')").append(" or ")
                .append("lower(c.zip) like lower('").append("%").append(query).append("%").append("')").append(" or ")
                .append("lower(c.country) like lower('").append("%").append(query).append("%").append("')");

        return sessionFactory.getCurrentSession().createQuery(queryStr.toString()).list();
    }
}
