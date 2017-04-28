package com.ishanvadwala.cmpe295b.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ishanvadwala.cmpe295b.R;
import com.ishanvadwala.cmpe295b.Adapters.SimpleFragmentAdapter;

/**
 * Created by ishanvadwala on 4/27/17.
 */
public class DataTabActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SimpleFragmentAdapter(getSupportFragmentManager(),
                DataTabActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

}
