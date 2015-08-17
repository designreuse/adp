package com.interval.dao;

import java.util.List;

/**
 * Created by User on 8/12/2015.
 */
public interface Dao<T> {

    public void create(T t);

    public void update(T t);

    public void get();

    public List<T> getAll();

    public void delete();
}
