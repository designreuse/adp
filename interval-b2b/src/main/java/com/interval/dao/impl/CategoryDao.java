package com.interval.dao.impl;

import com.interval.dao.models.Category;
import com.interval.dao.models.Center;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by USER on 12-08-2015.
 */

public class CategoryDao extends BaseDao<Category> {

    @Override
    public void create(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public void update(Category category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
    }

    @Override
    public Category get(final String categoryId) {
        Category category = null;
        try{
            category = (Category)sessionFactory.getCurrentSession().get(Category.class, Integer.parseInt(categoryId));
        }catch (NumberFormatException exc){

        }
        return category;
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
    public void delete(final String categoryId) {
        final Category category = get(categoryId);
        if(category != null) {
            sessionFactory.getCurrentSession().delete(category);
        }
    }
}
