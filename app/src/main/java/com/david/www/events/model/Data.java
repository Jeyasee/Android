package com.david.www.events.model;

import android.content.Context;

import java.util.List;

public class Data {
    private static List<Object> Restaurants;
    private static List<Object> Events;
    private static Object RestaurantObject;
    private static String PageFrom;
    public static Context context;

    private static String[] MapPoints;

    public void setRestaurants(List<Object> Restaurants){
        this.Restaurants =  Restaurants;
    }

    public Object getRestaurantsObject() {
        return this.RestaurantObject;
    }

    public void setRestaurantsObject(Object Restaurants){
        this.RestaurantObject =  Restaurants;
    }

    public List<Object> getRestaurants() {
        return this.Restaurants;
    }

    public void setEvents(List<Object> Events){
        this.Events =  Events;
    }

    public List<Object> getEvents() {
        return this.Events;
    }

    public void setPage(String Page){
        this.PageFrom =  Page;
    }

    public String getPage() {
        return this.PageFrom;
    }

    public void setMapPoints(String[] Page){
        this.MapPoints =  Page;
    }

    public String[] getMapPoints() {
        return this.MapPoints;
    }
}
