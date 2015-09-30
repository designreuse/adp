package com.interval.service.impl;

import com.interval.dao.impl.CenterDao;
import com.interval.dao.models.Category;
import com.interval.dao.models.Center;
import com.interval.dao.models.Product;
import com.interval.rest.models.CenterMenu;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Muthuraj on 9/30/2015.
 */
public class CommonService extends BaseService<Object> {

    private final CenterDao centerDao;

    @Inject
    public CommonService(CenterDao centerDao) {
        this.centerDao = centerDao;
    }

    @Override
    public List<Object> get(String id, String type) {
        List<Object> list = new ArrayList<Object>();
        if(type != null && type.equalsIgnoreCase("center")){
            Center center = centerDao.get(id);
            if(center != null){
                list.add(transformToCenterMenu(center));
            }else{
                list.add(null);
            }
        }else{
            list.add(null);
        }
        return list;
    }

    private CenterMenu transformToCenterMenu(Center center){
        CenterMenu centerMenu = new CenterMenu();
        Map<Category, List<Product>> categoryMap = new HashMap<Category, List<Product>>();
        for(Product product : center.getProducts()){
            if(product.getCategory() != null){
                if(!categoryMap.containsKey(product.getCategory())){
                    categoryMap.put(product.getCategory(), new ArrayList<Product>());
                }
                categoryMap.get(product.getCategory()).add(product);
            }
        }
        centerMenu.tranformToMenu(categoryMap);
        return centerMenu;
    }

}
