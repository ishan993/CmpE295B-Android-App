package com.ishanvadwala.cmpe295b.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ishanvadwala.cmpe295b.Controller.AppController;
import com.ishanvadwala.cmpe295b.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ishanvadwala on 5/1/17.
 */
public class StatusFragment extends Fragment {
    public static final String CROP_URL = "CROP_URL";
    String URL="http://54.200.196.145:3001/getMobileProduct";
    ImageView tempImg, atmImg, humidityImg;
    TextView tempTxt, atmTxt, humidityTxt;
    static String id;

    public static StatusFragment newInstance(String uid) {
        StatusFragment fragment = new StatusFragment();
        id = uid;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Log.d("Inside status Fragment", mURL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        tempImg = (ImageView)view.findViewById(R.id.tempImg);
        atmImg = (ImageView)view.findViewById(R.id.atmImg);
        humidityImg = (ImageView)view.findViewById(R.id.humidityImg);
        tempTxt = (TextView)view.findViewById(R.id.tempTxt);
        atmTxt = (TextView) view.findViewById(R.id.atmTxt);
        humidityTxt = (TextView) view.findViewById(R.id.humidityTxt);
        tempTxt.setText("Temperature");
        atmTxt.setText("Atmospheric Pressure");
        humidityTxt.setText("Humidity");

        try {
            setStatusImagesRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void setStatusImages(int temp, int hum, int atm){
        switch (temp){
            case 2:
                tempImg.setImageResource(R.drawable.good);
                break;
            case 1:
                tempImg.setImageResource(R.drawable.okay);
                break;
            case 0:
                tempImg.setImageResource(R.drawable.bad);
                break;
        }

        switch (hum){
            case 2:
                humidityImg.setImageResource(R.drawable.good);
                break;
            case 1:
                humidityImg.setImageResource(R.drawable.okay);
                break;
            case 0:
                humidityImg.setImageResource(R.drawable.bad);
                break;
        }

        switch (atm){
            case 2:
                atmImg.setImageResource(R.drawable.good);
                break;
            case 1:
                atmImg.setImageResource(R.drawable.okay);
                break;
            case 0:
                atmImg.setImageResource(R.drawable.bad);
                break;
        }
    }


    public void setStatusImagesRequest() throws JSONException {

        JSONObject jObj =  new JSONObject();
        jObj.put("uid", id);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL, jObj,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int temp = 0;
                        int hum = 0;
                        int atm = 0;
                        try {
                            Log.d("FUCKETY_FUCK_FUXK",response.getJSONObject(0).getInt("tempQuality")+"");
                            temp = response.getJSONObject(0).getInt("tempQuality");
                            hum = response.getJSONObject(0).getInt("humidityQuality");
                            atm = response.getJSONObject(0).getInt("pressureQuality");
                            setStatusImages(temp, hum, atm);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("STATUS_ERROR_ERROR", error.getMessage());
            }
        });
        AppController.getInstance().getRequestQueue().add(jsonArrayRequest);
    }
}
