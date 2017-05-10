package com.ishanvadwala.cmpe295b.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.MenuItem;

import com.ishanvadwala.cmpe295b.Model.TemperatureData;
import com.ishanvadwala.cmpe295b.Model.WeatherData;
import com.ishanvadwala.cmpe295b.R;
import com.ishanvadwala.cmpe295b.Adapters.SimpleFragmentAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;


/**
 * Created by ishanvadwala on 4/27/17.
 */
public class DataTabActivity extends AppCompatActivity {

    private CustomViewPager viewPager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tab);
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Data for farm");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
       // List<TemperatureData> list = getIntent().getParcelableArrayListExtra("tempList");
        HashMap<Integer, List<?>> map = (HashMap<Integer, List<?>>) getIntent().getExtras().get("tempList");
        String[] ids = (String[]) getIntent().getExtras().get("ids");
        Log.d("Does this work?", map.size() + "Dumbfuck");
        String summaryData = (String) getIntent().getExtras().get("summaryData");
        Log.d("SUMMARY_JSON", summaryData.toString());
        viewPager.setAdapter(new SimpleFragmentAdapter(getSupportFragmentManager(),
                DataTabActivity.this, getIntent().getStringExtra("CROP_URL"), map, summaryData, ids));
        tabLayout.setupWithViewPager(viewPager);
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

}
