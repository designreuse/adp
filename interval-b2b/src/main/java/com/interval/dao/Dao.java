package com.interval.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 8/12/2015.
 */
public interface Dao<T> {

    public void create(T t);

    public void update(T t);

    public T get(final String id);

    public List<T> getAll();

    public void delete(String id);

    public void updateById(final String id, final Map<String, Object> keyValueMap);

    public List<T> search(final String query);
}
