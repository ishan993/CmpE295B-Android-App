package com.ishanvadwala.cmpe295b.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.ishanvadwala.cmpe295b.R;

/**
 * Created by ishanvadwala on 5/8/17.
 */
public class TruckDataFragment extends Fragment{
    private LineChart lineChart;
    private BarChart barChart;
    private HorizontalBarChart horizontalBarChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_truck_graph, container, false);
        lineChart = (LineChart) view.findViewById(R.id.line_chart_truck);
        barChart = (BarChart) view.findViewById(R.id.bar_chart_truck);
        horizontalBarChart = (HorizontalBarChart) view.findViewById(R.id.horizontal_bar_chart);

        return view;
    }

    public static TruckDataFragment newInstance(){
        TruckDataFragment truckDataFragment = new TruckDataFragment();

        return truckDataFragment;
    }
}
