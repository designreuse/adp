package com.interval.service;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 8/12/2015.
 */
public interface Service<T> {

    public T create(T t);

    public T update(T  t);

    public void update(final String id, final String type, Map<Object, Object> params);

    public T get(final String id);

    public List<T> getAll();

    public void delete(final String id);

    public List<T> get(final String id, final String type, final Map<Object, Object> params);

    public List<T> search(final String query);
}
