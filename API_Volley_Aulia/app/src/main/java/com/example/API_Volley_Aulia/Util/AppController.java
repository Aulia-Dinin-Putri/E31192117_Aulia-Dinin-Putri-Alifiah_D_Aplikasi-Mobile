package com.example.API_Volley_Aulia.Util;

import android.app.Application;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application{
    private static final String TAG = Application.class.getSimpleName();
    private static AppController instance;
    RequestQueue mRequestQueque;
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized AppController getInstance(){ return instance;}
    private RequestQueue getRequestQueque(){
        if (mRequestQueque == null){
            mRequestQueque = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueque;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueque().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueque().add(req);
    }

    public void cancelAllRequest(Object req){
        if(mRequestQueque != null)
        {
            mRequestQueque.cancelAll(req);
        }
    }
}