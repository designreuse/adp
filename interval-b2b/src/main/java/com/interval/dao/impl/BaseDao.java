package com.interval.dao.impl;

import com.interval.dao.Dao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 8/12/2015.
 */
public class BaseDao implements Dao{

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
    public Criteria getAll() {
      return null;
    }

    @Override
    public void delete() {

    }
}
