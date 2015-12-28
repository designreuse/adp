package com.interval.service.impl;

import com.interval.common.Constants;
import com.interval.dao.impl.DashboardDao;
import com.interval.dao.query.DashboardQueryBuilder;
import com.interval.rest.models.MetricsByCenter;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 11/3/2015.
 */
public class DashboardService extends BaseService {

    private final DashboardDao dashboardDao;

    @Inject
    public DashboardService(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    @Override
    public List get(String id, String type, Map params) {
        List data = null;
        if(type != null){
            if(type.equals(Constants.PRODUCT_COUNT)){
                data = dashboardDao.search(DashboardQueryBuilder.productCountByCenter());
            }else if(type.equals(Constants.ORDER_COUNT)){
                data = dashboardDao.search(DashboardQueryBuilder.orderCountByCenter());
            }
        }
        return transformToMetricsByCenter(data, type);
    }

    private  List<MetricsByCenter> transformToMetricsByCenter(List<Object[]> rows, String type){
        List<MetricsByCenter> metricsByCenters = new ArrayList<MetricsByCenter>();
        for(Object[] row : rows){
            MetricsByCenter metricsByCenter = new MetricsByCenter();
            metricsByCenter.setId((Integer) row[0]);
            metricsByCenter.setName((String) row[1]);
            if(type.equals(Constants.PRODUCT_COUNT)){
                metricsByCenter.setProductCount(((BigInteger) row[2]).intValue());
            }else if(type.equals(Constants.ORDER_COUNT)){
                metricsByCenter.setOrderCount(((BigInteger) row[2]).intValue());
            }
            metricsByCenters.add(metricsByCenter);
        }
        return metricsByCenters;
    }
}
