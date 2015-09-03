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

    public int getId(final String key){
        int id = 0;
        try{
           id = Integer.parseInt(key);
        }catch (NumberFormatException exc){

        }
        return id;
    }

    @Override
    public void create(T t) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public T get(final String id) {
        return null;
    }

    @Override
    public List<T> getAll() {
      return null;
    }

   @Override
    public void delete(String id) {
   }

}
