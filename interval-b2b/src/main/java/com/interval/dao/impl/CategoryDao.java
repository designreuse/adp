package com.interval.dao.impl;

import com.interval.dao.Dao;
import com.interval.dao.models.Category;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by USER on 12-08-2015.
 */
@Service
public class CategoryDao implements Dao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create() {

    }

    @Override
    public void update() {

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
