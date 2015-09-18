package com.interval.dao.impl;

import com.interval.dao.models.Product;
import org.hibernate.Criteria;

import java.util.List;
import java.util.Map;

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

    @Override
    public void updateById(String id, Map<String, Object> keyValueMap) {
        final int productId = getId(id);
        if(productId > 0){
            final StringBuffer query = new StringBuffer("update Product set ");
            for(Map.Entry<String, Object> entry : keyValueMap.entrySet()){
                Object object = entry.getValue();
                query.append(entry.getKey()).append("=");
                if(object instanceof String){
                    query.append("'").append(entry.getValue()).append("'");
                }else{
                    query.append(entry.getValue());
                }
            }
        }
    }

}
