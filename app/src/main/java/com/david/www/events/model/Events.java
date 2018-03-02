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

public class Events extends AsyncTask {
    private String Error = null;
    String detail = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object... args) {

        HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.opendatamalta.org/ckan/dataset/dfdefba9-745e-4618-bddd-fbd179e910c8/resource/90f81009-fbc9-41ef-b031-fe3ae8bef03b/download/events.json");
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

        if (Error != null) {
            Log.d("","");
        } else {
            detail = (String) result;
            try{
                detail = "["+detail+"]";
                Gson gson = new Gson();

                Type t = new TypeToken<String[]>() {}.getType();
                List<Object> list = gson.fromJson(detail, new TypeToken<List<Object>>(){}.getType());

                com.david.www.events.activity.Events events = new com.david.www.events.activity.Events();
                events.Result(list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
