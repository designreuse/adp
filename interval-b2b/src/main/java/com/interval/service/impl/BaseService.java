package com.interval.service.impl;

import com.interval.service.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 9/15/2015.
 */
public class BaseService<T> implements Service<T> {

    @Override
    public T create(T t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public void update(String id, String type, Map<Object, Object> params) {

    }

    @Override
    public T get(String id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<T> get(String id, String type, Map<Object, Object> params) {
        return null;
    }

    @Override
    public List<T> search(String query) {
        return null;
    }
}
