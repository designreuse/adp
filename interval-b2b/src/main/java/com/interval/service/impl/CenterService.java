package com.interval.service.impl;

import com.interval.dao.impl.CenterDao;
import com.interval.dao.models.Center;
import com.interval.rest.models.RESTCenter;
import com.interval.service.Service;
import com.interval.transformers.CenterTransformer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/14/2015.
 */
public class CenterService implements Service<RESTCenter> {

    private final CenterDao centerDao;

    @Inject
    public CenterService(CenterDao centerDao) {
        this.centerDao = centerDao;
    }

    @Override
    public RESTCenter create(RESTCenter restCenter) {
        centerDao.create(CenterTransformer.transformCenter(restCenter));
        return null;
    }

    @Override
    public RESTCenter update(RESTCenter restCenter) {
        centerDao.update(CenterTransformer.transformCenter(restCenter));
        return restCenter;
    }

    @Override
    public RESTCenter get(final String centerId) {
        return CenterTransformer.transformRESTCenter(centerDao.get(centerId));
    }

    @Override
    public List<RESTCenter> getAll() {
        List<RESTCenter> centerList = new ArrayList<RESTCenter>();
        List<Center> centers = centerDao.getAll();
        for (Center center : centers) {
            centerList.add(CenterTransformer.transformRESTCenter(center));
        }
        return centerList;
    }

    @Override
    public void delete(final String centerId) {
        centerDao.delete(centerId);
    }
}
