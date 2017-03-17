package com.ishanvadwala.cmpe295b.Controller;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.ishanvadwala.cmpe295b.App;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController {
    private static AppController mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private AppController(){
        mRequestQueue = Volley.newRequestQueue(App.context);
        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    public static AppController getInstance(){
        if(mInstance == null){
            mInstance = new AppController();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }

}