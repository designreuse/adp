package com.interval.service.impl;

import com.interval.dao.impl.CategoryDao;
import com.interval.dao.impl.CenterDao;
import com.interval.dao.models.Center;
import com.interval.rest.models.RESTCenter;
import com.interval.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
    public RESTCenter create() {
        return null;
    }

    @Override
    public RESTCenter update(RESTCenter restCenter) {
        return null;
    }

    @Override
    public RESTCenter get() {
        return null;
    }

    @Override
    public List<RESTCenter> getAll() {
        List<RESTCenter> centerList = new ArrayList<RESTCenter>();
        List<Center> centers = centerDao.getAll();
        for(Center center : centers){
            RESTCenter restCenter = new RESTCenter();
            restCenter.setId(center.getId());
            restCenter.setName(center.getName());
            restCenter.setAddress1(center.getAddress1());
            restCenter.setAddress2(center.getAddress2());
            restCenter.setCity(center.getCity());
            restCenter.setState(center.getState());
            restCenter.setZip(center.getZip());
            restCenter.setCountry(center.getCountry());
            restCenter.setPhone(center.getPhone());
            restCenter.setEmail(center.getEmail());
            centerList.add(restCenter);
        }
        return centerList;
    }

    @Override
    public void delete() {

    }
}
