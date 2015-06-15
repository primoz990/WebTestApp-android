package com.example.webtestapp.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weblib.data.Company;
import com.example.weblib.data.Data;

import org.json.JSONObject;

import java.util.ArrayList;

public abstract class DataHelper extends Data {

    Context context;

    public DataHelper(Context context) {
        this.context = context;
    }

    @Override
    public boolean save(ArrayList<Company> companies) {
        //TODO implement caching in background thread
        return false;
    }

    @Override
    public void getObject() {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Company> companies = parse(response);//TODO parse in backgroud thread
                        if (companies.size() > 0) {
                            save(companies);
                        }
                        onSuccess(companies, false);//TODO implement caching
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onError(error.toString());
                    }
                });
        queue.add(jsObjRequest);
    }

    @Override
    public void getString() {
        new Exception("Method not implemeted!").printStackTrace();
    }
}
