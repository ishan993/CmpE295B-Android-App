package com.ishanvadwala.cmpe295b.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ishanvadwala.cmpe295b.Fragments.DataFragment;

/**
 * Created by ishanvadwala on 4/27/17.
 */

public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Temperature", "Pressure", "Humidity" };
    private Context context;

    public SimpleFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return DataFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
