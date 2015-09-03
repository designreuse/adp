package com.interval.dao.impl;

import com.interval.dao.models.Product;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by USER on 24-08-2015.
 */
public class ProductDao extends BaseDao<Product> {

    @Override
    public void create(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public Product get(final String productId) {
        Product product;
        final int id = getId(productId);
        product = (Product) sessionFactory.getCurrentSession().get(Product.class, id);
        return product;
    }

    @Override
    public List<Product> getAll() {
        final Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Product.class);
        return criteria.list();
    }

    @Override
    public void delete(final String productId) {
        final Product product = get(productId);
        if (product != null) {
            sessionFactory.getCurrentSession().delete(product);
        }
    }

}
