package com.interval.dao;

/**
 * Created by User on 8/12/2015.
 */
public interface Dao<T> {

    public void create();

    public void update();

    public void get();

    public T getAll();

    public void delete();
}
