package com.interval.service.impl;

import com.interval.dao.impl.CenterDao;
import com.interval.dao.models.Center;
import com.interval.dao.models.Screen;
import com.interval.dao.models.Show;
import com.interval.rest.models.RESTCenter;
import com.interval.service.Service;
import com.interval.transformers.CenterTransformer;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 8/14/2015.
 */
public class CenterService extends BaseService<RESTCenter> {

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
        Center center = CenterTransformer.transformCenter(restCenter);
        for(Screen screen : center.getScreens()){
            for(Show show : screen.getShows()){
                show.setScreen(screen);
            }
            screen.setCenter(center);
        }
        centerDao.update(center);
        if(!CollectionUtils.isEmpty(restCenter.getDeleteShowList())){
            centerDao.deleteShows(center.getId(), restCenter.getDeleteShowList());
        }
        if(!CollectionUtils.isEmpty(restCenter.getDeleteScreenList())){
            centerDao.deleteScreens(center.getId(), restCenter.getDeleteScreenList());
        }
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
