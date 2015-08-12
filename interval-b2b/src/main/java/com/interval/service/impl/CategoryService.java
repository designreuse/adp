package com.interval.service.impl;

import com.interval.dao.impl.CategoryDao;
import com.interval.dao.models.Category;
import com.interval.service.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by User on 8/12/2015.
 */
public class CategoryService implements Service {

    private final CategoryDao categoryDao;

    @Inject
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryDao.getAll();
        for(Category category : categories){
            category.getProducts();
        }
        return categories;
    }

    @Override
    public void get() {

    }

    @Override
    public void delete() {

    }
}
