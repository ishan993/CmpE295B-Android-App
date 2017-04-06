package com.ishanvadwala.cmpe295b.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.ishanvadwala.cmpe295b.Controller.AppController;
import com.ishanvadwala.cmpe295b.R;

import org.json.JSONArray;

/**
 * Created by ishanvadwala on 3/16/17.
 */
public class GraphActivity extends AppCompatActivity{

    final String DATA_URL ="http://54.187.201.38:3000/getdata";
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chart = (LineChart) findViewById(R.id.chart);
    }

    public JSONArray getData(){
        JSONArray responseArray = null;
        JsonArrayRequest arrayRequest = new JsonArrayRequest(DATA_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getMessage());
            }
        });

        AppController.getInstance().getRequestQueue().add(arrayRequest);
        return null;
    }

}
