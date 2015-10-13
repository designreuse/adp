package com.interval.service.impl;

import com.interval.dao.impl.CategoryDao;
import com.interval.dao.models.Category;
import com.interval.rest.models.RESTCategory;
import com.interval.transformers.CategoryTransformer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 8/12/2015.
 */
public class CategoryService extends BaseService<RESTCategory> {

    private final CategoryDao categoryDao;

    @Inject
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public RESTCategory create(RESTCategory restCategory) {
        Category category = CategoryTransformer.transformCategory(restCategory);
        category.setCreatedTime(new Date());
        categoryDao.create(category);
        return null;
    }

    @Override
    public RESTCategory update(RESTCategory restCategory) {
        Category category = CategoryTransformer.transformCategory(restCategory);
        category.setUpdatedTime(new Date());
        categoryDao.update(category);
        return restCategory;
    }

    @Override
    public List<RESTCategory> getAll() {
        List<Category> categories = categoryDao.getAll();
        List<RESTCategory> categoryList = new ArrayList<RESTCategory>();
        for (Category category : categories) {
            categoryList.add(CategoryTransformer.transformRESTCategory(category));
        }
        return categoryList;
    }

    @Override
    public RESTCategory get(final String categoryId) {
        Category category = categoryDao.get(categoryId);
        return CategoryTransformer.transformRESTCategory(category);
    }

    @Override
    public void delete(final String categoryId) {
        categoryDao.delete(categoryId);
    }
}
