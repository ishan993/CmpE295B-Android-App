package com.ishanvadwala.cmpe295b.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.ishanvadwala.cmpe295b.Controller.AppController;
import com.ishanvadwala.cmpe295b.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishanvadwala on 3/16/17.
 */
public class GraphFragment extends Fragment{
    android.support.v7.widget.Toolbar toolbar;
    final String DATA_URL ="http://54.187.201.38:3000/getdata";
    BarChart chart;
    TextView textView;

    public static GraphFragment newInstance(int page) {
        Bundle args = new Bundle();
        GraphFragment fragment = new GraphFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        chart = (BarChart) view.findViewById(R.id.chart);
        JSONArray result = getData();
        if(result != null){
            textView.setText(result.toString());
        }
        if(result != null)
            renderChart(result);

        return view;
    }

    public void renderChart(JSONArray result){

        List<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i =0; i<result.length(); i++) {

            // turn your data into Entry objects
            try {
                BarEntry entry = new BarEntry(((float) i),
                        Float.valueOf(result.getJSONObject(i).get("temperature").toString()));
                entries.add(entry);
            }catch(Exception E){
                Log.e("BArChart", E.getMessage());

            }

        }
        BarDataSet dataSet = new BarDataSet(entries, "Temperature"); // add entries to dataset
        dataSet.setColor(R.color.colorPrimary);
        dataSet.setValueTextColor(Color.WHITE);
        BarData barData = new BarData(dataSet);
        chart.setData(barData);
        chart.invalidate(); // refresh
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                // overridePendingTransition(R.animator.anim_left, R.animator.anim_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */
    public JSONArray getData(){
        Log.d("Response", "calling it!");
        final JSONArray[] responseArray = new JSONArray[1];
        JsonArrayRequest arrayRequest = new JsonArrayRequest(DATA_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                responseArray[0] = response;
                renderChart(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getMessage());
            }
        });

        AppController.getInstance().getRequestQueue().add(arrayRequest);
        if(responseArray[0] != null)
            return responseArray[0];


        return null;
    }

}
