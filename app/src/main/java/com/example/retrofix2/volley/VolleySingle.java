package com.example.retrofix2.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingle {
    private static VolleySingle mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    //Khỏi tạo constructor
    private VolleySingle (Context context) {
        mCtx= context;
        mRequestQueue =getRequestQueue();
    }
    //hàm lấy context
    public static synchronized VolleySingle getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingle (context);
        }
        return mInstance;
    }
    //Ham RequestQueue
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
// getApplicationContext() is key, it keeps you from Leaking the
// Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
    //Hàm addtoRequest
    public <T> void addToRequestQueue (Request<T> req) {
        getRequestQueue().add(req);
    }
    }