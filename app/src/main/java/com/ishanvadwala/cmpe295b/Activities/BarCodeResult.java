package com.ishanvadwala.cmpe295b.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.google.zxing.Result;
import com.ishanvadwala.cmpe295b.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by ishanvadwala on 4/6/17.
 */
public class BarCodeResult extends AppCompatActivity {
    private TextView textView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.bar_code_result);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.barCodeResult);
        textView.setText(intent.getStringExtra("result"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("result"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

}
