package com.ishanvadwala.cmpe295b.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.zxing.Result;
import com.ishanvadwala.cmpe295b.Controller.AppController;
import com.ishanvadwala.cmpe295b.Model.HumidityData;
import com.ishanvadwala.cmpe295b.Model.PressureData;
import com.ishanvadwala.cmpe295b.Model.TemperatureData;
import com.ishanvadwala.cmpe295b.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by ishanvadwala on 5/1/17.
 */
public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    LinearLayout scannerLayout;
    private Toolbar toolbar;
    private ZXingScannerView zXingScannerView;
    private String ROOT_URL =  "http://54.200.196.145:3001/";
    private int[] counter;
    public static int[] staticCounter;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Farm Produce Traceability");
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        scannerLayout = (LinearLayout)findViewById(R.id.scannerLayout);

        scannerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanner();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(zXingScannerView != null)
            zXingScannerView.stopCamera();
    }

    public void startScanner(){
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(ScannerActivity.this);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.d("SCAN_RESULT", result.toString());
        zXingScannerView.stopCamera();
        String[] ids = result.toString().split("-");
        getData(ids);
    }


    public List getData(final String[] idArray) {

        final HashMap<Integer,List<?>> resultMap = new HashMap<>();
        final JSONArray[] responseArray = new JSONArray[1];
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("farmId", "f001");
            jsonObject.put("startDate", "03/01/2017");
            jsonObject.put("endDate", "06/01/2017");
        }catch (Exception e){
            Log.d("EXCEPTION", e.toString());
        }
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, ROOT_URL+"getMobileData", jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                HashMap<Integer, List<?>> map = new HashMap<>();
                responseArray[0] = response;
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                List<TemperatureData> tempList = new ArrayList<>();
                List<PressureData> pressList = new ArrayList<>();
                List<HumidityData> humList = new ArrayList<>();
                counter = new int[2];
                for (int i = 0; i < responseArray[0].length(); i++) {
                    try {
                        JSONObject jsonObject = responseArray[0].getJSONObject(i);

                        Date date = df.parse(jsonObject.getString("date"));
                        TemperatureData tempData = new TemperatureData(jsonObject.getString("_id"),
                                jsonObject.getDouble("temperature"), date);
                        PressureData pressData = new PressureData(jsonObject.getString("_id"),
                                jsonObject.getDouble("pressure"), date);
                        HumidityData humData = new HumidityData(jsonObject.getString("_id"),
                                jsonObject.getDouble("humidity"), date);
                        if(jsonObject.getBoolean("alert"))
                            counter[1]++;
                        else
                            counter[0]++;

                        humList.add(humData);
                        tempList.add(tempData);
                        pressList.add(pressData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                setErrorCount(counter);
                map.put(0, tempList);
                map.put(1, pressList);
                map.put(2, humList);
                Intent intent = new Intent(ScannerActivity.this, DataTabActivity.class);
                intent.putExtra("idArray", idArray);
                Log.d("FinalSize", tempList.size() + "");
                intent.putExtra("tempList", map);
               // startActivity(intent);
                try {
                    getSummaryData(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("I got this:", error.toString());
                Intent errorIntent = new Intent(ScannerActivity.this, ScannerActivity.class);
                startActivity(errorIntent);
            }
        });


        AppController.getInstance().getRequestQueue().add(arrayRequest);


        return null;
    }

    public void getSummaryData(final Intent intent) throws JSONException {
        JSONObject jObj = new JSONObject();
        String[] ids = (String[]) intent.getExtras().get("idArray");
        jObj.put("uid", ""+ids[0]);
        jObj.put("farmId", ""+ids[1]);
        jObj.put("year", "2017");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                ROOT_URL + "getSummaryData", jObj, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("SUMMARY", response.toString());
                    intent.putExtra("summaryData", response.getJSONObject(0).toString());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR_ERROR_ERROR", error.getMessage());
                Intent errorIntent = new Intent(ScannerActivity.this, ScannerActivity.class);
                startActivity(errorIntent);
            }
        });


        AppController.getInstance().getRequestQueue().add(request);
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

    public static void setErrorCount(int[] counter){
        staticCounter = counter;
    }
    public static int[] getErrorCount(){
        return staticCounter;
    }

}
