package com.david.www.events.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.www.events.R;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class EventsRecycler extends RecyclerView.Adapter<EventsRecycler.ViewHolder> {
    //public Object tour_list;
    public ArrayList<Object> events;

    public EventsRecycler(ArrayList<Object> Item){
        events = Item;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title,type,venue,location,date,identifier;

        public ViewHolder(View itemView){
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.event_title);
            type = (TextView)itemView.findViewById(R.id.event_type);
            venue = (TextView)itemView.findViewById(R.id.venue);
            location = (TextView)itemView.findViewById(R.id.location);
            date = (TextView)itemView.findViewById(R.id.date);
            identifier = (TextView)itemView.findViewById(R.id.identifier);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.evnts_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        try {
            viewHolder.title.setText(((LinkedTreeMap) (events.get(position))).get("Title").toString());
        }catch (Exception e){
            viewHolder.title.setText("Not Available");
        }
        try {
            viewHolder.type.setText(((LinkedTreeMap) (events.get(position))).get("EventType").toString());
        }catch (Exception e){
            viewHolder.type.setText("Not Available");
        }
        try {
            viewHolder.venue.setText(((LinkedTreeMap) (events.get(position))).get("Venue").toString());
        }catch (Exception e){
            viewHolder.venue.setText("Not Available");
        }
        try{
            viewHolder.location.setText(((LinkedTreeMap)(events.get(position))).get("Location").toString());
        }catch (Exception e){
            viewHolder.location.setText("Not Available");
        }
        try{
            viewHolder.date.setText(((LinkedTreeMap)(events.get(position))).get("EventDate").toString());
        }catch (Exception e){
            viewHolder.date.setText("Not Available");
        }
        try{
            viewHolder.identifier.setText(((LinkedTreeMap)(events.get(position))).get("Identifier").toString());
        }catch (Exception e){
            viewHolder.identifier.setText("Not Available");
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
