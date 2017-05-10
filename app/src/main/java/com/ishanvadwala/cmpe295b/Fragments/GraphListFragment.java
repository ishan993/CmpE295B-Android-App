package com.ishanvadwala.cmpe295b.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.ishanvadwala.cmpe295b.Activities.ScannerActivity;
import com.ishanvadwala.cmpe295b.Adapters.ChartListAdapter;
import com.ishanvadwala.cmpe295b.Model.HumidityData;
import com.ishanvadwala.cmpe295b.Model.PressureData;
import com.ishanvadwala.cmpe295b.Model.TemperatureData;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_graph_list_fragment);

        List<BarData> barDataList = produceBarData();
        List<PieData> pieDataList = producePieData();

        list = (ArrayList<? extends Parcelable>) getArguments().getParcelableArrayList("list");
        HashMap<Integer, List<?>> lineEntriesMap = getLineEntriesMap(list);
        List<LineData> lineDataList = getLineDataList(lineEntriesMap);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("FUCKETY FUCK FUCK", "you");

        recyclerView.setAdapter(new ChartListAdapter(getContext(), lineDataList, barDataList, pieDataList));


        return view;
    }

    public List<BarData> produceBarData(){
        float min = getArguments().getFloat("min");
        float avg = getArguments().getFloat("avg");
        float max = getArguments().getFloat("max");

        Utils.init(getContext());

        List<BarData> barDataList = new ArrayList<>();
        BarEntry entry1 =  new BarEntry(1, min);
        List<BarEntry> list1 = new ArrayList<>();
        list1.add(entry1);
        BarDataSet barDataSet1 = new BarDataSet(list1, "Min");
        barDataSet1.setColor(ColorTemplate.MATERIAL_COLORS[0]);


        BarEntry entry2 =  new BarEntry(2, avg);
        List<BarEntry> list2 = new ArrayList<>();
        list2.add(entry2);
        BarDataSet barDataSet2 = new BarDataSet(list2, "Avg");
        barDataSet2.setColor(Color.parseColor("#FFEE58"));


        BarEntry entry3 =  new BarEntry(3, max);
        List<BarEntry> list3 = new ArrayList<>();
        list3.add(entry3);
        BarDataSet barDataSet3 = new BarDataSet(list3, "Max");
        barDataSet1.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);

        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3);

        barDataList.add(barData);
        return barDataList;
    }

    public List<PieData> producePieData(){
        List<PieData> pieDataList = new ArrayList<>();
        List<PieEntry> pieEntries = getPieChartEntry();

        for(int i=0; i<5; i++){
            PieDataSet pieDataSet = new PieDataSet(pieEntries,"Quarter "+i);
            pieDataSet.setValueTextSize(12);
            pieDataSet.setSliceSpace(3);
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS[0], ColorTemplate.VORDIPLOM_COLORS[4]);
            PieData pieData = new PieData(pieDataSet);
            pieDataList.add(pieData);
        }

        return pieDataList;
    }



    public List<PieEntry> getPieChartEntry(){
        List<PieEntry> pieEntries = new ArrayList<>();
        int[] counter = ScannerActivity.getErrorCount();
        String[] label = new String[]{"No Alert", "Alert"};
        for(int i=0;i<2; i++){
            PieEntry entry = new PieEntry(counter[i], label[i]);
            pieEntries.add(entry);
        }
        return pieEntries;
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
                    map.put(((TemperatureData) list.get(i)).getMonth(), tempList);
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
                    map.put(((HumidityData) list.get(i)).getMonth(), tempList);
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
                    map.put(((PressureData) list.get(i)).getMonth(), tempList);
                }else
                    tempList = (List<Entry>) map.get(((PressureData) list.get(i)).getMonth());

                Entry entry = new Entry();
                PressureData pressData = (PressureData) list.get(i);
                entry.setX(pressData.getDay());
                entry.setY((float) pressData.getAtm());
                tempList.add(entry);
            }
        }
        Log.d("I doooooo1 get called",""+map.size());

        return map;
    }

    public List<LineData> getLineDataList(HashMap<Integer, List<?>> map){
        List<LineData> lineDataList = new ArrayList<>();

        for(int key: map.keySet()) {
            List<Entry> entryList = (List<Entry>) map.get(key);
            LineDataSet tempLineDataSet = new LineDataSet(entryList, "Daily Value");
            tempLineDataSet.setDrawValues(false);
            Utils.init(getContext());
            tempLineDataSet.setColor(ColorTemplate.VORDIPLOM_COLORS[key]);
            tempLineDataSet.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[key]);
            tempLineDataSet.setLineWidth(1.3f);
            tempLineDataSet.setCircleHoleRadius(2f);
            tempLineDataSet.setCircleRadius(3.5f);
            LineData lineData = new LineData(tempLineDataSet);
            lineDataList.add(lineData);
        }
        Log.d("I doooooo get called", ""+lineDataList.size());
        return lineDataList;
    }

}
