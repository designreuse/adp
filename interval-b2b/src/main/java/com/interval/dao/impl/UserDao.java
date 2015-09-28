package com.interval.dao.impl;

import com.interval.dao.models.User;

import java.util.List;

/**
 * Created by User on 24/9/15.
 */
public class UserDao extends BaseDao<User>{

    @Override
    public void create(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public User get(String id) {
        final int userID = getId(id);
        return (User) sessionFactory.getCurrentSession().get(User.class, userID);
    }

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
    }

    @Override
    public void delete(String id) {
        User user = get(id);
        if(user != null){
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
