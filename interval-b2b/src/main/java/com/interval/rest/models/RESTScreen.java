package com.interval.rest.models;

import java.util.Date;
import java.util.Set;

/**
 * Created by User on 8/15/2015.
 */
public class RESTScreen {
    private Integer id;
    private String name;
    private Date createdTime;
    private Date updatedTime;
    private Set<RESTShow> shows;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Set<RESTShow> getShows() {
        return shows;
    }

    public void setShows(Set<RESTShow> shows) {
        this.shows = shows;
    }
}
