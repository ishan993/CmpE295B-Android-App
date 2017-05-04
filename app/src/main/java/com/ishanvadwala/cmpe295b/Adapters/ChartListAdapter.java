package com.ishanvadwala.cmpe295b.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;
import com.ishanvadwala.cmpe295b.R;

import java.util.List;

/**
 * Created by ishanvadwala on 3/9/17.
 */
// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ChartListAdapter extends RecyclerView.Adapter<ChartListAdapter.ViewHolder> {

    private LineChart lineChart;
    private HorizontalBarChart hBarChart;
    private PieChart pieChart;
    private List<LineData> lineDataList;
    private List<BarData> barDataList;
    private List<PieData> pieDataList;
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View chartsItem = inflater.inflate(R.layout.charts_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(chartsItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        lineChart = holder.lineChart;
        hBarChart = holder.hBarChart;
        pieChart = holder.pieChart;

        lineChart.setData(lineDataList.get(position));
        lineChart.animateXY(2000, 2000);
        hBarChart.setData(barDataList.get(position));
        hBarChart.animateXY(1000, 2000);
        pieChart.setData(pieDataList.get(position));
        pieChart.animateXY(1000, 1000);
        pieChart.setHoleRadius(40);
    }

    @Override
    public int getItemCount() {
        return lineDataList.size();
    }

    public ChartListAdapter(Context context, List<LineData> lineDataList,
                            List<BarData> barDataList, List<PieData> pieDataList){
        this.context = context;
        this.lineDataList = lineDataList;
        this.barDataList = barDataList;
        this.pieDataList = pieDataList;
    }

    private Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private LineChart lineChart;
        private HorizontalBarChart hBarChart;
        private PieChart pieChart;


        public ViewHolder(View itemView) {
            super(itemView);
            lineChart = (LineChart) itemView.findViewById(R.id.list_item_line_chart);
            hBarChart = (HorizontalBarChart) itemView.findViewById(R.id.list_item_hbar_chart);
            pieChart = (PieChart) itemView.findViewById(R.id.list_item_pie_chart);
        }
    }
}