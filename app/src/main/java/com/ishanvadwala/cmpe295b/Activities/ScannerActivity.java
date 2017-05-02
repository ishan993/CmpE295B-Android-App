package com.ishanvadwala.cmpe295b.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.google.zxing.Result;
import com.ishanvadwala.cmpe295b.R;

import java.util.Scanner;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by ishanvadwala on 5/1/17.
 */
public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    LinearLayout scannerLayout;
    private Toolbar toolbar;
    private ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Farm Produce Traceability");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        scannerLayout = (LinearLayout)findViewById(R.id.scannerLayout);

        scannerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanner();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(zXingScannerView != null)
            zXingScannerView.stopCamera();
    }

    public void startScanner(){
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(ScannerActivity.this);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.d("SCAN_RESULT", result.toString());
        zXingScannerView.stopCamera();
        Intent intent = new Intent(this, DataTabActivity.class);
        intent.putExtra("CROP_URL", result.getText());
        startActivity(intent);
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
