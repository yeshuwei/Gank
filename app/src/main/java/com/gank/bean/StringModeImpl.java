package com.gank.bean;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gank.app.VolleySingleton;
import com.gank.interfaze.OnStringListener;

/**
 * Created by 11033 on 2017/3/4.
 * onStringListener实现类
 */

public class StringModeImpl  {
    private Context context;

    public StringModeImpl(Context context) {
        this.context = context;
    }

    public void load(String url, final OnStringListener listener){
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listener.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError);
            }
        });
        VolleySingleton.getVolleySingletonl(context).addToRequestQueue(request);
    }
}