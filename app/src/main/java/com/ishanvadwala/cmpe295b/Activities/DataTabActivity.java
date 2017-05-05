package com.ishanvadwala.cmpe295b.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ishanvadwala.cmpe295b.Controller.AppController;
import com.ishanvadwala.cmpe295b.Model.HumidityData;
import com.ishanvadwala.cmpe295b.Model.PressureData;
import com.ishanvadwala.cmpe295b.Model.TemperatureData;
import com.ishanvadwala.cmpe295b.Model.WeatherData;
import com.ishanvadwala.cmpe295b.R;
import com.ishanvadwala.cmpe295b.Adapters.SimpleFragmentAdapter;

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


/**
 * Created by ishanvadwala on 4/27/17.
 */
public class DataTabActivity extends AppCompatActivity {

    final String DATA_URL = "http://54.200.196.145:3001/getMobileData";
    List<WeatherData> weatherDataList;
    private ViewPager viewPager;
    private int[] counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SimpleFragmentAdapter(getSupportFragmentManager(),
                DataTabActivity.this, getIntent().getStringExtra("CROP_URL"), null));
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        getData();
    }

    public void refreshViewPager(HashMap<Integer, List<?>> resultMap) {
        if(resultMap != null) {
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setAdapter(new SimpleFragmentAdapter(getSupportFragmentManager(),
                    DataTabActivity.this, getIntent().getStringExtra("CROP_URL"), resultMap));
        }
    }

    public List getData() {

        final HashMap<Integer,List<?>> resultMap = new HashMap<>();
        final JSONArray[] responseArray = new JSONArray[1];
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("farmId", "f001");
            jsonObject.put("startDate", "04-01-2017");
            jsonObject.put("endDate", "05-01-2017");
        }catch (Exception e){
            Log.d("EXCEPTION", e.toString());
        }
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, DATA_URL, jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                responseArray[0] = response;
                DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
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
                            counter[0]++;
                        else
                            counter[1]++;

                        humList.add(humData);
                        tempList.add(tempData);
                        pressList.add(pressData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                resultMap.put(0, tempList);
                resultMap.put(1, humList);
                resultMap.put(2, pressList);
                refreshViewPager(resultMap);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("I got this:", error.toString());

            }
        });


        AppController.getInstance().getRequestQueue().add(arrayRequest);


        return null;
    }
}
