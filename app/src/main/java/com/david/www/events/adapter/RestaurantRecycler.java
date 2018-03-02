package com.david.www.events.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.www.events.R;
import com.david.www.events.activity.MainActivity;
import com.google.gson.internal.LinkedTreeMap;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RestaurantRecycler extends RecyclerView.Adapter<RestaurantRecycler.ViewHolder> {
    //public Object tour_list;
    public ArrayList<Object> tour_detail;

    public RestaurantRecycler(ArrayList<Object> Item){
        tour_detail = Item;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,telephone,address,website,hours,points,gps,identifier;

        public ViewHolder(View itemView){
            super(itemView);
            try{
                name = (TextView)itemView.findViewById(R.id.restaurant_name);
                telephone = (TextView)itemView.findViewById(R.id.restaurant_phone);
                address = (TextView)itemView.findViewById(R.id.address);
                website = (TextView)itemView.findViewById(R.id.website);
                hours = (TextView)itemView.findViewById(R.id.hour);
                //gps = (TextView)itemView.findViewById(R.id.gps);
                identifier = (TextView)itemView.findViewById(R.id.identifier);
            }catch (Exception e){}
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        try {
            viewHolder.name.setText(((LinkedTreeMap) (tour_detail.get(position))).get("name").toString());
        }catch (Exception e){
            viewHolder.name.setText("Not Available");
        }
        try {
            viewHolder.telephone.setText("Phn : "+ ((LinkedTreeMap) (tour_detail.get(position))).get("telephone").toString());
        }catch (Exception e){
            viewHolder.telephone.setText("Not Available");
        }
        try {
            viewHolder.address.setText(((LinkedTreeMap) (tour_detail.get(position))).get("address").toString());
        }catch (Exception e){
            viewHolder.address.setText("Not Available");
        }
        try{
            viewHolder.website.setText(((LinkedTreeMap)(tour_detail.get(position))).get("website").toString());
        }catch (Exception e){
            viewHolder.website.setText("Not Available");
        }
        try{
            viewHolder.hours.setText(((LinkedTreeMap)(tour_detail.get(position))).get("opening-hours").toString());
        }catch (Exception e){
            viewHolder.hours.setText("Not Available");
        }

        try{
            String points = ((LinkedTreeMap)(tour_detail.get(position))).get("latitude").toString() + "-"
                    +((LinkedTreeMap)(tour_detail.get(position))).get("longitude").toString()+ "-"
                    +((LinkedTreeMap) (tour_detail.get(position))).get("name").toString();
            /*viewHolder.gps.setContentDescription(points);*/
            viewHolder.identifier.setContentDescription(points);
        }catch (Exception e){
            viewHolder.points.setText("");
        }

        /*viewHolder.gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = new MainActivity();
                main.goToLocation(v.getContentDescription().toString());
            }
        });*/

        viewHolder.identifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = new MainActivity();
                main.goToLocation(v.getContentDescription().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tour_detail.size();
    }
}
