package com.ishanvadwala.cmpe295b.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ishanvadwala.cmpe295b.R;

/**
 * Created by ishanvadwala on 5/9/17.
 */
public class ErrorActivity extends AppCompatActivity{
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);

        button = (Button) findViewById(R.id.error_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ErrorActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}
