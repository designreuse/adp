package com.interval.service.impl;

import com.interval.dao.impl.ProductDao;
import com.interval.dao.models.Product;
import com.interval.rest.models.RESTProduct;
import com.interval.service.Service;
import com.interval.transformers.ProductTransformer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 24-08-2015.
 */
public class ProductService implements Service<RESTProduct> {

    private final ProductDao productDao;

    @Inject
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public RESTProduct create(RESTProduct restProduct) {
        productDao.create(ProductTransformer.transformProduct(restProduct));
        return null;
    }

    @Override
    public RESTProduct update(RESTProduct restProduct) {
        productDao.update(ProductTransformer.transformProduct(restProduct));
        return restProduct;
    }

    @Override
    public RESTProduct get(final String productId) {
        return ProductTransformer.transformRESTProduct(productDao.get(productId));
    }

    @Override
    public List<RESTProduct> getAll() {
        List<RESTProduct> productList = new ArrayList<RESTProduct>();
        List<Product> products = productDao.getAll();
        for (Product product : products) {
            productList.add(ProductTransformer.transformRESTProduct(product));
        }
        return productList;
    }

    @Override
    public void delete(final String productId) {
        productDao.delete(productId);
    }
}
