package com.ishanvadwala.cmpe295b.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.ishanvadwala.cmpe295b.Adapters.ChartListAdapter;
import com.ishanvadwala.cmpe295b.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishanvadwala on 5/3/17.
 */
public class GraphListFragment extends Fragment{
    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Fragment newInstance(){
        return new GraphListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_graph_list_fragment);
        List<LineData> lineDataList = produceLineData();
        List<BarData> barDataList = produceBarData();
        List<PieData> pieDataList = producePieData();
        recyclerView.setAdapter(new ChartListAdapter(getContext(),lineDataList, barDataList, pieDataList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public List<LineData> produceLineData(){
        List<LineData> lineDataList = new ArrayList<>();
        List<Entry> entryList = getLineChartEntries();
        for(int i = 0; i<5; i++){
            LineDataSet tempLineDataSet = new LineDataSet(entryList, "Temperatures");
            tempLineDataSet.setDrawValues(false);
            Utils.init(getContext());
            tempLineDataSet.setColor(ColorTemplate.MATERIAL_COLORS[0]);
            tempLineDataSet.setLineWidth(1.3f);
            tempLineDataSet.setCircleHoleRadius(2f);
            tempLineDataSet.setCircleRadius(3.5f);
            LineData tempLineData = new LineData(tempLineDataSet);
            lineDataList.add(tempLineData);
        }
        return lineDataList;
    }

    public List<BarData> produceBarData(){
        List<BarData> barDataList = new ArrayList<>();
        List<BarEntry> entryList = getBarChartEntries();
        for(int i = 0; i<5; i++){
            BarDataSet tempBarDataSet = new BarDataSet(entryList, "Monthly Averages");
            Utils.init(getContext());
            tempBarDataSet.setColor(ColorTemplate.MATERIAL_COLORS[1]);
            BarData tempBarData = new BarData(tempBarDataSet);
            barDataList.add(tempBarData);
        }
        return barDataList;
    }

    public List<PieData> producePieData(){
        List<PieData> pieDataList = new ArrayList<>();
        List<PieEntry> pieEntries = getPieChartEntry();

        for(int i=0; i<5; i++){
            PieDataSet pieDataSet = new PieDataSet(pieEntries,"Quarter "+i);
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            PieData pieData = new PieData(pieDataSet);
            pieDataList.add(pieData);
        }

        return pieDataList;
    }


    public List<Entry> getLineChartEntries(){
        int[] sampleData =new int[]{22,33,34,34,23,33,36,22,20,28,24,36,40,43,20,34,34,
                23,40,32,22,11,33,36,22,20,43,20,34,34,23,33,36,22,33,34,34,23,33,36,22,20,28,24,36,40,43,20,34,34,
                23,40,32,22,11,33,36,22,20,43,20,34,34,23,33,36, 22,33,34,34,23,33,36,22,20,28,24,36,40,43,20,34,34,
                23,40,32,22,11,33,36,22,20,43,20,34,34,23,33,36,22,33,34,34,23,33,36,22,20,28,24,36,40,43,20,34,34,
                23,40,32,22,11,33,36,22,20,43,20,34,34,23,33,36};
        List<Entry> entryList = new ArrayList<>();
        for(int i=0;i<120; i++){
            Entry entry = new Entry();
            entry.setX(i);
            entry.setY(Float.valueOf(sampleData[i]));
            entryList.add(entry);
        }
        return entryList;
    }

    public List<BarEntry> getBarChartEntries(){
        List<BarEntry> entryList = new ArrayList<>();
        for(int i=1;i<4; i++){
            BarEntry entry = new BarEntry(i, i*25, "Ishan"+i);
            entryList.add(entry);
        }
        return entryList;
    }

    public List<PieEntry> getPieChartEntry(){
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i=1; i<3; i++) {
            PieEntry pieEntry1 = new PieEntry(i+9, "Good");
            pieEntries.add(pieEntry1);
        }

        return pieEntries;
    }
}
