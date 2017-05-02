package com.ishanvadwala.cmpe295b.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ishanvadwala.cmpe295b.R;

/**
 * Created by ishanvadwala on 5/1/17.
 */
public class StatusFragment extends Fragment {
    public static final String CROP_URL = "CROP_URL";
    String mURL;
    ImageView tempImg, atmImg, humidityImg;
    TextView tempTxt, atmTxt, humidityTxt;

    public static StatusFragment newInstance(String URL) {
        Bundle args = new Bundle();
        args.putString(CROP_URL, URL);
        StatusFragment fragment = new StatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mURL = getArguments().getString(CROP_URL);
        Log.d("Inside status Fragment", mURL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        tempImg = (ImageView)view.findViewById(R.id.tempImg);
        atmImg = (ImageView)view.findViewById(R.id.atmImg);
        humidityImg = (ImageView)view.findViewById(R.id.humidityImg);
        tempTxt = (TextView)view.findViewById(R.id.tempTxt);
        atmTxt = (TextView) view.findViewById(R.id.atmTxt);
        humidityTxt = (TextView) view.findViewById(R.id.humidityTxt);
        tempTxt.setText("Temperature");
        atmTxt.setText("Atmospheric Pressure");
        humidityTxt.setText("Humidity");
        return view;
    }
}
