package com.interval.dao.impl;

import com.interval.dao.Dao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by User on 8/12/2015.
 */
public class BaseDao<T> implements Dao<T>{

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void create(T t) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public void get() {

    }

    @Override
    public List<T> getAll() {
      return null;
    }

    @Override
    public void delete() {

    }
}
