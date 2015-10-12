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

    @Override
    public List<Center> search(String query) {
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @Override
    public void execute(String query) {
        sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
    }

    @Override
    public void executeSQL(String query) {
        sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
    }
}
