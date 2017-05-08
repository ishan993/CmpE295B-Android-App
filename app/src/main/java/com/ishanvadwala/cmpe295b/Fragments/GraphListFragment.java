package com.ishanvadwala.cmpe295b.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.ishanvadwala.cmpe295b.Model.HumidityData;
import com.ishanvadwala.cmpe295b.Model.PressureData;
import com.ishanvadwala.cmpe295b.Model.TemperatureData;
import com.ishanvadwala.cmpe295b.Model.WeatherData;
import com.ishanvadwala.cmpe295b.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ishanvadwala on 5/3/17.
 */
public class GraphListFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<?> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("onCreate====>>>>", getArguments().toString());
    }

    public static Fragment newInstance(int position, Bundle savedInstanceState){
        GraphListFragment graphListFragmentInstance = new GraphListFragment();

        if(savedInstanceState != null){

            graphListFragmentInstance.setArguments(savedInstanceState);
            Log.d("==============>>>>", graphListFragmentInstance.getArguments().toString());
        }
        return graphListFragmentInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_graph_list_fragment);

        List<BarData> barDataList = produceBarData();
        List<PieData> pieDataList = producePieData();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = (ArrayList<? extends Parcelable>) getArguments().getParcelableArrayList("list");
        HashMap<Integer, List<?>> lineEntriesMap = getLineEntriesMap(list);
        List<LineData> lineDataList = getLineDataSetMap(lineEntriesMap);
        recyclerView.setAdapter(new ChartListAdapter(getContext(), lineDataList, barDataList, pieDataList));


        return view;
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
            pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
            PieData pieData = new PieData(pieDataSet);
            pieDataList.add(pieData);
        }

        return pieDataList;
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
        for(int i=1; i<8; i++) {
            PieEntry pieEntry1 = new PieEntry(i+9, "Good");
            pieEntries.add(pieEntry1);
        }

        return pieEntries;
    }

    public List<LineData> getLineDataList(List<?> list){
        List<Entry> entriesList = new ArrayList<>();
        String label = "";
        HashMap<Integer, List<?>> map = new HashMap<>();


        LineDataSet lineDataSet = new LineDataSet(entriesList, label);
        lineDataSet.setDrawValues(false);
        Utils.init(getContext());
        lineDataSet.setColor(ColorTemplate.MATERIAL_COLORS[0]);
        lineDataSet.setLineWidth(1.3f);
        lineDataSet.setCircleHoleRadius(2f);
        lineDataSet.setCircleRadius(3.5f);
        List<LineData> lineDataList = new ArrayList<>();
        LineData lineData = new LineData(lineDataSet);
        lineDataList.add(lineData);

        Log.d("MAP_SIZE----->", map.size()+"");

        return lineDataList;
    }

    public HashMap<Integer, List<?>> getLineEntriesMap(List<?> list){
        HashMap<Integer, List<?>> map = new HashMap<>();
        String label="";

        List<Entry> tempList;
        if(list.get(0) instanceof TemperatureData){
            label = "Temperature Data";


            for(int i =0; i < list.size(); i++){

                if ((List<Entry>) map.get(((TemperatureData) list.get(i)).getMonth())==null){
                    tempList = new ArrayList<>();
                }else
                    tempList = (List<Entry>) map.get(((TemperatureData) list.get(i)).getMonth());

                Entry entry = new Entry();
                TemperatureData tempData = (TemperatureData) list.get(i);
                entry.setX(tempData.getDay());
                entry.setY((float) tempData.getTemperature());
                tempList.add(entry);

            }
        }else if(list.get(0) instanceof HumidityData){
            label = "Humidity Data";

            for(int i =0; i < list.size(); i++){

                if ((List<Entry>) map.get(((HumidityData) list.get(i)).getMonth())==null){
                    tempList = new ArrayList<>();
                }else
                    tempList = (List<Entry>) map.get(((HumidityData) list.get(i)).getMonth());

                Entry entry = new Entry();
                HumidityData humData = (HumidityData) list.get(i);
                entry.setX(humData.getDay());
                entry.setY((float) humData.getHumidity());
                tempList.add(entry);
            }
        }else if(list.get(0) instanceof PressureData){
            label = "Pressure Data";
            for (int i=0; i<list.size(); i++){

                if ((List<Entry>) map.get(((PressureData) list.get(i)).getMonth())==null){
                    tempList = new ArrayList<>();
                }else
                    tempList = (List<Entry>) map.get(((PressureData) list.get(i)).getMonth());

                Entry entry = new Entry();
                PressureData pressData = (PressureData) list.get(i);
                entry.setY(pressData.getDay());
                entry.setX((float) pressData.getAtm());
                tempList.add(entry);
            }
        }
        return map;
    }

    public List<LineData> getLineDataSetMap(HashMap<Integer, List<?>> map){
        List<LineData> lineDataList = new ArrayList<>();

        for(int key: map.keySet()) {
            List<Entry> entryList = (List<Entry>) map.get(key);
            LineDataSet tempLineDataSet = new LineDataSet(entryList, "Daily Value");
            tempLineDataSet.setDrawValues(false);
            Utils.init(getContext());
            tempLineDataSet.setColor(ColorTemplate.MATERIAL_COLORS[key]);
            tempLineDataSet.setLineWidth(1.3f);
            tempLineDataSet.setCircleHoleRadius(2f);
            tempLineDataSet.setCircleRadius(3.5f);
            LineData lineData = new LineData(tempLineDataSet);
            lineDataList.add(lineData);
        }
        return lineDataList;
    }

}
