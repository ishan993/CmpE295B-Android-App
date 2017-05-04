package com.ishanvadwala.cmpe295b.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.ishanvadwala.cmpe295b.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishanvadwala on 5/1/17.
 */
public class LineChartFragment extends Fragment {
    private LineChart lineChart;
    private HorizontalBarChart horizontalBarChart;

    public static LineChartFragment newInstance() {
      //  Bundle args = new Bundle();
        LineChartFragment fragment = new LineChartFragment();
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);
        horizontalBarChart = (HorizontalBarChart)view.findViewById(R.id.horizontal_bar_chart);

        LineData lineData = produceLineChartData();
        lineChart.setData(lineData);

        BarData barData = produceBarChartData();
        horizontalBarChart.setData(barData);
        horizontalBarChart.animateXY(1000, 1000);

        return view;
    }

    public BarData produceBarChartData(){
        List<BarEntry> list = new ArrayList<>();
        List<BarEntry> list2 = new ArrayList<>();
        List<BarEntry> list3 = new ArrayList<>();

        int[] colors = new int[3];
        colors[0] = Color.GREEN;
        colors[1] = Color.CYAN;
        colors[2] = Color.RED;


        BarEntry barEntry =  new BarEntry(1, 25, "Weather");
        list.add(barEntry);

        BarDataSet barDataSet = new BarDataSet(list, "Ishan");
        barDataSet.setColor(colors[0]);

        BarEntry barEntrySecond =  new BarEntry(2, 50, "Atmosphere");
        list2.add(barEntrySecond);
        BarDataSet barDataSet2 = new BarDataSet(list2, "Atm");
        barDataSet2.setColor(colors[1]);

        BarEntry barEntryThird =  new BarEntry(3, 75, "Humidity");
        list3.add(barEntryThird);
        BarDataSet barDataSet3 = new BarDataSet(list3, "Humidity");
        barDataSet3.setColor(colors[2]);


        BarData barData = new BarData(barDataSet, barDataSet2, barDataSet3);

        return barData;
    }

    public LineData produceLineChartData(){
        List<Entry> list = produceDataForChart();
        LineDataSet dataSet = new LineDataSet(list, "DumbFuck");
        dataSet.setColor(Color.CYAN);
        dataSet.setValueTextColor(Color.DKGRAY);
        dataSet.setValueTextSize(8);
        dataSet.setLineWidth(3.5f);
        dataSet.setCircleRadius(5f);
        dataSet.setCircleColor(Color.CYAN);
        dataSet.setCircleHoleRadius(2.5f);

        list = anotherOne();
        LineDataSet dataSetGreen = new LineDataSet(list, "Ishan");
        dataSetGreen.setColor(Color.GREEN);
        dataSetGreen.setValueTextColor(Color.BLACK);
        dataSetGreen.setValueTextSize(8);
        dataSetGreen.setLineWidth(3.5f);
        dataSetGreen.setCircleRadius(5f);
        dataSetGreen.setCircleColor(Color.GREEN);
        dataSetGreen.setCircleHoleRadius(2.5f);


        LineData lineData = new LineData(dataSet, dataSetGreen);
        return lineData;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            lineChart.animateXY(1000, 1000);
            lineChart.invalidate();
        }

    }

    public List<Entry> produceDataForChart(){
        List<Entry> list = new ArrayList<>();
        for(int i=0; i<12; i++){
            Entry temp = new Entry();
            int tempInt = i;
            if(i%2 ==0)
                tempInt = -i;

            temp.setX(i);
            temp.setY(tempInt*23);
            list.add(temp);
        }
        return list;
    }
    public List<Entry> anotherOne(){
        List<Entry> list = new ArrayList<>();
        for(int i=0; i<12; i++){
            Entry temp = new Entry();
            temp.setX(i);
            temp.setY(i*32);
            list.add(temp);
        }
        return list;
    }
}
