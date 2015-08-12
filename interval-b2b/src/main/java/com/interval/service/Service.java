package com.interval.service;

/**
 * Created by User on 8/12/2015.
 */
public interface Service<T> {

    public void create();

    public void update();

    public void get();

    public T getAll();

    public void delete();
}
