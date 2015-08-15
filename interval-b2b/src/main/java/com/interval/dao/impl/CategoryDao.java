package com.interval.dao.impl;

import com.interval.dao.models.Category;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by USER on 12-08-2015.
 */

public class CategoryDao extends BaseDao<Category> {

    @Override
    public void create() {

    }

    @Override
    public void update(Category category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
    }

    @Override
    public void get() {

    }

    @Override
    public List<Category> getAll() {
        final Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Category.class);
        List<Category> categories = criteria.list();
        System.out.println("categories size : " + categories.size());
        return categories;
    }

    @Override
    public void delete() {

    }
}
