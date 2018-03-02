package com.david.www.events.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.david.www.events.R;
import com.david.www.events.adapter.Markers;
import com.david.www.events.model.Data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.internal.LinkedTreeMap;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    private static GoogleMap mMap;
    private View view;
    private ClusterManager<Markers> mClusterManager;
    public static Data data;
    public static Context context;
    private static SupportMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.activity_maps, container, false);

        if(mapFragment == null){
            mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
        }

        if(mapFragment != null)
            mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Data data = new Data();
        googleMap.clear();
        mClusterManager = new ClusterManager<Markers>(getContext(), googleMap);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);

        if(data.getPage() != "Restaurant"){
            List<Object> restaurants = data.getRestaurants();

            try{
                addItems();
            }catch (Exception e){
            }

        }else{
            String[] points = data.getMapPoints();
            Double lat = Double.valueOf(points[0]);
            Double lng = Double.valueOf(points[1]);
            String name = points[2];

            Markers myMarkers = new Markers(lat, lng, name, "");

            mClusterManager.addItem(myMarkers);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lng)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng), 12.0f));

            data.setPage("");
        }
    }

    public void addItems(){
        Data data = new Data();
        List<Object> restaurants = data.getRestaurants();

        try{
            if(restaurants != null){
                for(Object list:restaurants){
                    String name = "";
                    double lat = 0,lng = 0;
                    try{
                        lat = Double.valueOf(((LinkedTreeMap) list).get("latitude").toString());
                    }catch (Exception e){
                        lat = 0;
                    }
                    try{
                        lng = Double.valueOf(((LinkedTreeMap) list).get("longitude").toString());
                    }catch (Exception e){
                        lng = 0;
                    }

                    try{
                        name = ((LinkedTreeMap) list).get("name").toString();
                    }catch (Exception e){
                        name = "Not available";
                    }

                    if(lat != 0 && lng != 0){
                        Markers offsetItem = new Markers(lat, lng,name,"");
                        mClusterManager.addItem(offsetItem);
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lng), 11.5f));
                    }

                }
            }

        }catch (Exception e){
            Log.d(e.toString(),"Restaurant lat lang");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
