package com.ishanvadwala.cmpe295b.Activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;
import com.ishanvadwala.cmpe295b.Adapters.MainAdapter;
import com.ishanvadwala.cmpe295b.R;

import org.json.JSONArray;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    String[] imageURLs;
    String[] controls;
    private ZXingScannerView zXingScannerView;

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
                Log.d("MAINACT LOG", "clicked settings item");
                return true;
            case R.id.QRCodeItem:
                zXingScannerView = new ZXingScannerView(this);
                setContentView(zXingScannerView);
                zXingScannerView.setResultHandler(this);
                zXingScannerView.startCamera();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.d("SCAN_RESULT", result.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        zXingScannerView.stopCamera();
        Intent intent = new Intent(this, BarCodeResult.class);
        intent.putExtra("result", result.getText());
        startActivity(intent);
    }
}
