package com.interval.service.impl;

import com.interval.dao.impl.UserDao;
import com.interval.dao.models.User;
import com.interval.rest.models.RESTUser;
import com.interval.transformers.UserTransformer;
import org.apache.commons.collections.CollectionUtils;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by User on 24/9/15.
 */
public class ProfileService extends BaseService<RESTUser> {

    private final UserDao userDao;

    @Inject
    public ProfileService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public RESTUser create(RESTUser restUser) {
        userDao.create(UserTransformer.transformUser(restUser));
        return null;
    }

    @Override
    public RESTUser update(RESTUser restUser) {
        userDao.update(UserTransformer.transformUser(restUser));
        return restUser;
    }

    @Override
    public RESTUser get(String id) {
        return UserTransformer.transformRESTUser(userDao.get(id));
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public List<RESTUser> getAll() {
        final List<RESTUser> restUserList = new ArrayList<RESTUser>();
        final List<User> userList = userDao.getAll();
        for(final User user : userList){
            restUserList.add(UserTransformer.transformRESTUser(user));
        }
        return restUserList;
    }

    @Override
    public List<RESTUser> search(final String query) {
        final List<RESTUser> restUserList = new ArrayList<RESTUser>();
        final List<User> userList= userDao.search(query);
        for(final User user : userList){
            restUserList.add(UserTransformer.transformRESTUser(user));
        }
        return restUserList;
    }
}
