package com.ishanvadwala.cmpe295b.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.JsonReader;
import android.util.Log;

import com.ishanvadwala.cmpe295b.Fragments.GraphFragment;
import com.ishanvadwala.cmpe295b.Fragments.GraphListFragment;
import com.ishanvadwala.cmpe295b.Fragments.LineChartFragment;
import com.ishanvadwala.cmpe295b.Fragments.StatusFragment;
import com.ishanvadwala.cmpe295b.Fragments.TruckDataFragment;
import com.ishanvadwala.cmpe295b.Model.HumidityData;
import com.ishanvadwala.cmpe295b.Model.TemperatureData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ishanvadwala on 4/27/17.
 */

public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    HashMap<Integer, List<?>> map;
    String uid;
    final int PAGE_COUNT = 4;
    private JSONObject summaryJObj;
    private String tabTitles[] = new String[] { "Status", "Temperature", "Humidity", "Pressure"};
    private Context context;
    private String CROP_URL;
    Bundle tempBundle, humBundle, pressureBundle;
    public SimpleFragmentAdapter(FragmentManager fm, Context context, String URL,
                                 HashMap<Integer, List<?>> map, String summaryData, String[] ids) {
        super(fm);
        CROP_URL = URL;

        if(map != null) {
            Log.d("ListSize: ",map.size()+"");
            this.map = map;
            tempBundle = new Bundle();
            humBundle = new Bundle();
            pressureBundle = new Bundle();
            try {
                summaryJObj = new JSONObject(summaryData);
                tempBundle.putFloat("min", Float.valueOf(String.valueOf(summaryJObj.get("minTemp"))));
                tempBundle.putFloat("avg", Float.valueOf(String.valueOf(summaryJObj.get("avgTemp"))));
                tempBundle.putFloat("max", Float.valueOf(summaryJObj.get("maxTemp").toString()));

                humBundle.putFloat("max", Float.valueOf(summaryJObj.get("maxHum").toString()));
                humBundle.putFloat("avg", Float.valueOf(summaryJObj.get("avghumidity").toString()));
                humBundle.putFloat("min", Float.valueOf(summaryJObj.get("minHum").toString()));

                pressureBundle.putFloat("max", Float.valueOf(summaryJObj.get("maxAtm").toString()));
                pressureBundle.putFloat("avg", Float.valueOf(summaryJObj.get("avgPressure").toString()));
                pressureBundle.putFloat("min", Float.valueOf(summaryJObj.get("minAtm").toString()));
            }catch (JSONException e){
                Log.d("ADAPTER_JOBJ_ERROR", e.getMessage());
            }

            Log.d("ADAPTER", summaryData);
            tempBundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) map.get(0));
            humBundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) map.get(2));
            pressureBundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) map.get(1));

            uid = ids[0];

        }else
            Log.d("TagTagTag", "I got null");



        this.context = context;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return StatusFragment.newInstance(uid);
            case 1:
                return GraphListFragment.newInstance(position, tempBundle);
            case 2:
                return GraphListFragment.newInstance(position, humBundle);
            case 3:
                return GraphListFragment.newInstance(position, pressureBundle);
            default:
                return TruckDataFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
