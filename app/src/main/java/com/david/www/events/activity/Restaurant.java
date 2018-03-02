package com.david.www.events.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.david.www.events.R;
import com.david.www.events.adapter.RestaurantRecycler;
import com.david.www.events.model.Data;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class Restaurant extends Fragment {
    public Context context;
    public static View view;
    private static Data data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.restaurant_list, container, false);

        data = new Data();

        try{
            if(data.getRestaurants().size() == 0){
                new com.david.www.events.model.Restaurant().execute();
            }else
            {
                this.Result(data.getRestaurantsObject());
            }
        }catch (Exception e){
            new com.david.www.events.model.Restaurant().execute();
        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void Result(Object result) {
        Log.d("","");

        try{

            data.setRestaurantsObject(result);

            Object restaurants = ((LinkedTreeMap) ((ArrayList) result).get(0)).get("restaurants");
            restaurants = ((LinkedTreeMap) restaurants).get("restaurant");
            ArrayList<Object> restaurantList = ((ArrayList) restaurants);

            //Set Restaurants list
            data.setRestaurants(restaurantList);

            RecyclerView mRecycularView = (RecyclerView) view.findViewById(R.id.restaurant_recycler);
            mRecycularView.setHasFixedSize(true);

            RecyclerView.LayoutManager linerLayout = new LinearLayoutManager(context);
            mRecycularView.setLayoutManager(linerLayout);

            //RestaurantRecycler restaurantRecycler= new RestaurantRecycler();
            RecyclerView.Adapter<RestaurantRecycler.ViewHolder> adapter = new RestaurantRecycler(restaurantList);
            mRecycularView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
