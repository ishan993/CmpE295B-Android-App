package com.ishanvadwala.cmpe295b.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ishanvadwala.cmpe295b.Model.TemperatureData;
import com.ishanvadwala.cmpe295b.Model.WeatherData;
import com.ishanvadwala.cmpe295b.R;
import com.ishanvadwala.cmpe295b.Adapters.SimpleFragmentAdapter;

import java.util.HashMap;
import java.util.List;


/**
 * Created by ishanvadwala on 4/27/17.
 */
public class DataTabActivity extends AppCompatActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
       // List<TemperatureData> list = getIntent().getParcelableArrayListExtra("tempList");
        HashMap<Integer, List<?>> map = (HashMap<Integer, List<?>>) getIntent().getExtras().get("tempList");
        Log.d("Does this work?", map.size()+"Dumbfuck");

        viewPager.setAdapter(new SimpleFragmentAdapter(getSupportFragmentManager(),
                DataTabActivity.this, getIntent().getStringExtra("CROP_URL"), map));
        tabLayout.setupWithViewPager(viewPager);
    }

}
