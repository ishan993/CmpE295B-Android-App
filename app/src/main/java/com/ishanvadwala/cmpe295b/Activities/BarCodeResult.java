package com.ishanvadwala.cmpe295b.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.ishanvadwala.cmpe295b.Controller.AppController;
import com.ishanvadwala.cmpe295b.R;

import org.json.JSONArray;


/**
 * Created by ishanvadwala on 4/6/17.
 */
public class BarCodeResult extends AppCompatActivity {
    private TextView textView;
    private Toolbar toolbar;

    final String DATA_URL ="http://54.187.201.38:3000/getdata";
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.bar_code_result);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.barCodeResult);
        textView.setText(intent.getStringExtra("result"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("result"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        JSONArray result = getData();
        if(result != null){
            textView.setText(result.toString());
        }
    }

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

    public JSONArray getData(){
        Log.d("Response", "calling it!");
        final JSONArray[] responseArray = new JSONArray[1];
        JsonArrayRequest arrayRequest = new JsonArrayRequest(DATA_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response", response.toString());
                responseArray[0] = response;
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
