package com.interval.service;

import java.util.List;

/**
 * Created by User on 8/12/2015.
 */
public interface Service<T> {

    public T create(T t);

    public T update(T  t);

    public T get(final String id);

    public List<T> getAll();

    public void delete(final String id);

    public T get(final String id, final String type);
}
