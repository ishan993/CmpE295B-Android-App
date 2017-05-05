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

            tempBundle.putParcelableArrayList("tempBundle", (ArrayList<? extends Parcelable>) map.get(0));
            humBundle.putParcelableArrayList("humBundle", (ArrayList<? extends Parcelable>) map.get(1));
            pressureBundle.putParcelableArrayList("pressBundle", (ArrayList<? extends Parcelable>) map.get(2));
            List<TemperatureData> list = tempBundle.getParcelableArrayList("tempBundle");

        }else
            Log.d("TagTagTag", "I got null");



        this.context = context;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    @Override
    public Fragment getItem(int position) {
        Log.d("Rerendering", position+" ");
        if(position == 0)
            return StatusFragment.newInstance(CROP_URL);
        if(position == 1) {
            if(tempBundle  !=null)
                Log.d("Data", tempBundle.toString());

                return GraphListFragment.newInstance(tempBundle);
        }

        return GraphFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
