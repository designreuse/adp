package com.interval.service;

import java.util.List;

/**
 * Created by User on 8/12/2015.
 */
public interface Service<T> {

    public T create();

    public T update(T  t);

    public T get();

    public List<T> getAll();

    public void delete();
}
