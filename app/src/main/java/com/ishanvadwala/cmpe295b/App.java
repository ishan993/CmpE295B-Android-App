package com.ishanvadwala.cmpe295b;

import android.app.Application;
import android.content.Context;

/**
 * Created by ishanvadwala on 3/16/17.
 */
public class App extends Application {
    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
