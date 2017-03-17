package com.ishanvadwala.cmpe295b.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.ishanvadwala.cmpe295b.Controller.AppController;
import com.ishanvadwala.cmpe295b.Model.Readings;
import com.ishanvadwala.cmpe295b.R;

import java.util.List;

/**
 * Created by ishanvadwala on 3/9/17.
 */
// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Readings> readingsList;
    private String[] imageUrls;
    private String[] category;
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.cardview_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NetworkImageView networkImageView = holder.networkImageView;
        TextView textView = holder.textView;
        textView.setText(category[position]);
        networkImageView.setImageUrl(imageUrls[position], AppController.getInstance().getImageLoader());
    }

    @Override
    public int getItemCount() {
        return category.length;
    }

    public MainAdapter(Context context, String[] controls, String[] imageUrls) {
        this.context = context;
        this.category = controls;
        this.imageUrls = imageUrls;
    }

    private Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public NetworkImageView networkImageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.networkImageView);
            textView = (TextView) itemView.findViewById(R.id.optionText);
        }
    }
}