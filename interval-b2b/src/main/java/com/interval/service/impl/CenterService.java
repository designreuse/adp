package com.interval.service.impl;

import com.interval.dao.impl.CenterDao;
import com.interval.dao.models.Center;
import com.interval.dao.models.Screen;
import com.interval.dao.models.Show;
import com.interval.rest.models.RESTCenter;
import com.interval.rest.models.RESTScreen;
import com.interval.rest.models.RESTShow;
import com.interval.service.Service;

import javax.inject.Inject;
import java.util.*;

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
        return null;
    }

    @Override
    public RESTCenter update(RESTCenter restCenter) {
        centerDao.update(toCenter(restCenter));
        return restCenter;
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
            centerList.add(toRESTCenter(center));
        }
        return centerList;
    }

    @Override
    public void delete() {

    }

    private RESTCenter toRESTCenter(Center center){
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
        restCenter.setScreens(toRESTScreen(center.getScreens()));
        return restCenter;
    }

    private Center toCenter(RESTCenter restCenter){
        Center center = new Center();
        center.setId(restCenter.getId());
        center.setName(restCenter.getName());
        center.setAddress1(restCenter.getAddress1());
        center.setAddress2(restCenter.getAddress2());
        center.setCity(restCenter.getCity());
        center.setState(restCenter.getState());
        center.setZip(restCenter.getZip());
        center.setCountry(restCenter.getCountry());
        center.setPhone(restCenter.getPhone());
        center.setEmail(restCenter.getEmail());
        center.setScreens(toScreen(restCenter.getScreens(), restCenter.getId()));
        for(Screen screen : center.getScreens()){
            screen.setCenter(center);
        }
        return center;
    }

    private Set<Screen> toScreen(Set<RESTScreen> restScreens, int centerId){
        System.out.println("restScreens list size " + restScreens.size());
        Set<Screen> screens = new HashSet<Screen>(restScreens.size());
        for(RESTScreen restScreen : restScreens){
            Screen screen = new Screen();
            screen.setId(restScreen.getId());
            screen.setName(restScreen.getName());
            screen.setCreatedTime(restScreen.getCreatedTime());
            screen.setUpdatedTime(new Date());
            screen.setShows(toShow(restScreen.getShows()));
            for(Show show : screen.getShows()){
                show.setScreen(screen);
            }
            screens.add(screen);
        }
        return screens;
    }

    private Set<RESTScreen> toRESTScreen(Set<Screen> screens){
        System.out.println("screen list size " + screens.size());
        Set<RESTScreen> restScreens = new HashSet<RESTScreen>(screens.size());
        for(Screen screen : screens){
            RESTScreen restScreen = new RESTScreen();
            restScreen.setId(screen.getId());
            restScreen.setName(screen.getName());
            restScreen.setCreatedTime(screen.getCreatedTime());
            restScreen.setUpdatedTime(screen.getUpdatedTime());
            restScreen.setShows(toRESTShow(screen.getShows()));
            restScreens.add(restScreen);
        }
        return restScreens;
    }

    private Set<Show> toShow(Set<RESTShow> restShows){
        System.out.println("restShows list size " + restShows.size());
        Set<Show> shows = new HashSet<Show>(restShows.size());
        for(RESTShow restShow : restShows){
            Show show = new Show();
            show.setId(restShow.getId());
            show.setTime(restShow.getTime());
            show.setCreatedTime(restShow.getCreatedTime());
            show.setUpdatedTime(new Date());
            shows.add(show);
        }
        return shows;
    }

    private Set<RESTShow> toRESTShow(Set<Show> shows){
        System.out.println("shows list size " + shows.size());
        Set<RESTShow> restShows = new HashSet<RESTShow>(shows.size());
        for(Show show : shows){
            RESTShow restShow = new RESTShow();
            restShow.setId(show.getId());
            restShow.setTime(show.getTime());
            restShow.setCreatedTime(show.getCreatedTime());
            restShow.setUpdatedTime(show.getUpdatedTime());
            restShows.add(restShow);
        }
        return restShows;
    }
}
