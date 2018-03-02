package com.david.www.events.model;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.lang.reflect.Type;
import java.util.List;

public class Restaurant extends AsyncTask {
    private String Error = null;
    String detail = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object... args) {

        HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.opendatamalta.org/ckan/dataset/1d5df898-1481-42cf-85a5-673c6cdec65e/resource/73e13022-0ae6-445e-9bb5-d3d91f154a7b/download/restaurants.json");
        httpget.addHeader("Content-Type","application/json");

        ResponseHandler<String> handler = new BasicResponseHandler();
        Object result = new Object();

        try {
            result = client.execute(httpget, handler);
        } catch (Exception ex) {
            Error = ex.getMessage();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        try{
            if (Error != null) {
                Log.d("","");
            } else {
                detail = (String) result;
                try{
                    detail = "["+detail+"]";
                    Gson gson = new Gson();

                    Type t = new TypeToken<String[]>() {}.getType();
                    List<Object> list = gson.fromJson(detail, new TypeToken<List<Object>>(){}.getType());

                    com.david.www.events.activity.Restaurant restaurant = new com.david.www.events.activity.Restaurant();
                    restaurant.Result(list);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            Log.d("","");
        }
    }

}
