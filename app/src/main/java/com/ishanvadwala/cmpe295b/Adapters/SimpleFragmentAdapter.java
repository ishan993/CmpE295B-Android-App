package com.ishanvadwala.cmpe295b.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.ishanvadwala.cmpe295b.Fragments.GraphFragment;
import com.ishanvadwala.cmpe295b.Fragments.GraphListFragment;
import com.ishanvadwala.cmpe295b.Fragments.LineChartFragment;
import com.ishanvadwala.cmpe295b.Fragments.StatusFragment;
import com.ishanvadwala.cmpe295b.Model.HumidityData;
import com.ishanvadwala.cmpe295b.Model.TemperatureData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ishanvadwala on 4/27/17.
 */

public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    HashMap<Integer, List<?>> map;
    final int PAGE_COUNT = 5;
    private String tabTitles[] = new String[] { "Status","Line Chart", "Temperature", "Pressure", "Humidity" };
    private Context context;
    private String CROP_URL;
    Bundle tempBundle, humBundle, pressureBundle;
    public SimpleFragmentAdapter(FragmentManager fm, Context context, String URL, HashMap<Integer, List<?>> map) {
        super(fm);
        CROP_URL = URL;

        if(map != null) {
            Log.d("ListSize: ",map.size()+"");
            this.map = map;
            tempBundle = new Bundle();
            humBundle = new Bundle();
            pressureBundle = new Bundle();

            tempBundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) map.get(0));
            humBundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) map.get(1));
            pressureBundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) map.get(2));

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
                return StatusFragment.newInstance("Hahahahah, Dumbfuck");
            case 1:
                return GraphListFragment.newInstance(position, tempBundle);
            case 2:
                return GraphListFragment.newInstance(position, humBundle);
            case 3:
                return GraphListFragment.newInstance(position, pressureBundle);
            default:
                return GraphFragment.newInstance(1);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
