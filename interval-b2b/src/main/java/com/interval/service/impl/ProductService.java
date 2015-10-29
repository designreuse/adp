package com.interval.service.impl;

import com.interval.dao.impl.ProductDao;
import com.interval.dao.models.Product;
import com.interval.dao.query.ProductQueryBuilder;
import com.interval.rest.models.RESTProduct;
import com.interval.transformers.ProductTransformer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 24-08-2015.
 */
public class ProductService extends BaseService<RESTProduct> {

    private final ProductDao productDao;

    @Inject
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public RESTProduct create(RESTProduct restProduct) {
        Product product = ProductTransformer.transformProduct(restProduct);
        product.setCreatedTime(new Date());
        productDao.create(product);
        return null;
    }

    @Override
    public RESTProduct update(RESTProduct restProduct) {
        Product product = ProductTransformer.transformProduct(restProduct);
        product.setUpdatedTime(new Date());
        productDao.update(product);
        return restProduct;
    }

    @Override
    public RESTProduct get(final String productId) {
        return ProductTransformer.transformRESTProduct(productDao.get(productId));
    }

    @Override
    public List<RESTProduct> get(String id, String type, Map<Object, Object> params) {
        List<Product> products = null;
        List<RESTProduct> restProducts = new ArrayList<RESTProduct>();
        if(type != null){
            if(type.equalsIgnoreCase("center")){
                products = productDao.search(ProductQueryBuilder.getProductsByCenter(id));
            }
            if(products != null){
                for(Product product : products){
                    restProducts.add(ProductTransformer.transformRESTProduct(product));
                }
            }
        }
        return restProducts;
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
