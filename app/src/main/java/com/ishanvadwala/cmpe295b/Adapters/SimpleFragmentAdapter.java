package com.ishanvadwala.cmpe295b.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.ishanvadwala.cmpe295b.Fragments.GraphFragment;
import com.ishanvadwala.cmpe295b.Fragments.StatusFragment;

/**
 * Created by ishanvadwala on 4/27/17.
 */

public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Status", "Temperature", "Pressure", "Humidity" };
    private Context context;
    private String CROP_URL;

    public SimpleFragmentAdapter(FragmentManager fm, Context context, String URL) {
        super(fm);
        CROP_URL = URL;
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return StatusFragment.newInstance(CROP_URL);
        return GraphFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
