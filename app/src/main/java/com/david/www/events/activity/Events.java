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
import com.david.www.events.adapter.EventsRecycler;
import com.david.www.events.model.Data;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class Events extends Fragment {
    private static Context context;
    private static View view;
    public static Data data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_list, container, false);

        try{
            if(data.getEvents().size() == 0){
                new com.david.www.events.model.Events().execute();
            }else
            {
                this.Result(data.getEvents());
            }
        }catch (Exception e){
            new com.david.www.events.model.Events().execute();
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

    public void Result(List<Object> result) {
        Log.d("","");

        data = new Data();

        try{
            ArrayList<Object> events = ( (ArrayList) ((LinkedTreeMap) ((ArrayList) result).get(0)).get("EventList"));

            //Set Restaurants list
            data.setEvents(events);

            RecyclerView mRecycularView = (RecyclerView) this.view.findViewById(R.id.event_recycler);
            mRecycularView.setHasFixedSize(true);

            RecyclerView.LayoutManager linerLayout = new LinearLayoutManager(context);
            mRecycularView.setLayoutManager(linerLayout);

            //RestaurantRecycler restaurantRecycler= new RestaurantRecycler();
            RecyclerView.Adapter<EventsRecycler.ViewHolder> adapter = new EventsRecycler(events);
            mRecycularView.setAdapter(adapter);
        }catch (Exception e){
            Log.d("","");
        }

    }
}
