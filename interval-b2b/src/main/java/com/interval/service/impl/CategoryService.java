package com.interval.service.impl;

import com.interval.dao.impl.CategoryDao;
import com.interval.dao.models.Category;
import com.interval.rest.models.RESTCategory;
import com.interval.service.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 8/12/2015.
 */
public class CategoryService implements Service<RESTCategory> {

    private final CategoryDao categoryDao;

    @Inject
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public RESTCategory create() {
        return null;
    }

    @Override
    public RESTCategory update(RESTCategory restCategory) {
        Category category = new Category();
        category.setId(restCategory.getId());
        category.setDescription(restCategory.getDescription());
        category.setName(restCategory.getName());
        category.setUpdatedTime(new Date());
        categoryDao.update(category);
        return null;
    }

    @Override
    public List<RESTCategory> getAll() {
        List<Category> categories = categoryDao.getAll();
        List<RESTCategory> categoryList = new ArrayList<RESTCategory>();
        for(Category category : categories){
            RESTCategory restCategory = new RESTCategory();
            restCategory.setId(category.getId());
            restCategory.setDescription(category.getDescription());
            restCategory.setName(category.getName());
            categoryList.add(restCategory);
        }
        return categoryList;
    }

    @Override
    public RESTCategory get() {
        return null;
    }

    @Override
    public void delete() {

    }
}
