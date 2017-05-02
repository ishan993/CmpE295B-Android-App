package com.ishanvadwala.cmpe295b.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ishanvadwala.cmpe295b.Adapters.MainAdapter;
import com.ishanvadwala.cmpe295b.Fragments.GraphFragment;
import com.ishanvadwala.cmpe295b.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    String[] imageURLs;
    String[] controls;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        imageURLs = new String[]
                {"http://www.concordpianoservice.com/wp-content/uploads/2015/06/humidity1-672x372.jpg",
        "https://cdn.pixabay.com/photo/2016/01/05/10/15/sunset-1122188_960_720.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Low_pressure_system_over_Iceland.jpg/800px-Low_pressure_system_over_Iceland.jpg"
        };
        controls = new String[]{"Humidity", "Weather", "Pressure"};

        MainAdapter adapter = new MainAdapter(this, controls, imageURLs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public RequestQueue getRequestQueue(){
        RequestQueue queue = Volley.newRequestQueue(this);
        return queue;
    }



    public void setWeatherData(JSONArray response){

    }

    public ArrayList<String> getWeatherData(){

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settingsItem:
                    Intent intent = new Intent(this, GraphFragment.class);
                    startActivity(intent);
                return true;
            case R.id.QRCodeItem:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
