package com.example.webtestapp;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.example.weblib.Login;
import com.example.weblib.LoginInterface;
import com.example.weblib.WebResult;

import java.util.HashMap;
import java.util.Map;

public abstract class LoginHelper extends Login {
    Context context;

    public LoginHelper(Context context) {
        this.context = context;
    }

    @Override
    public void login(String username, String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        /*
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                LoginInterface.URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onLoginSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onLoginFailed(error.networkResponse.statusCode, error.getMessage());
            }
        });
        queue.add(stringRequest);
*/
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", getAuthHeader(username, password));
        LoginRequest loginRequest = new LoginRequest(LoginInterface.URL, WebResult.class, headers, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                onLoginSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onLoginFailed(error.networkResponse.statusCode, error.getMessage());
            }
        });
        queue.add(loginRequest);

    }

    public class LoginRequest<T> extends Request<T> {
        private final Class<T> clazz;
        private final Map<String, String> headers;
        private final Response.Listener<T> listener;

        /**
         * Make a GET request and return a parsed object from JSON.
         *
         * @param url     URL of the request to make
         * @param clazz   Relevant class object, for Gson's reflection
         * @param headers Map of request headers
         */
        public LoginRequest(String url, Class<T> clazz, Map<String, String> headers,
                            Response.Listener<T> listener, Response.ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            this.clazz = clazz;
            this.headers = headers;
            this.listener = listener;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers != null ? headers : super.getHeaders();
        }

        @Override
        protected void deliverResponse(T response) {
            listener.onResponse(response);
        }

        @Override
        protected Response<T> parseNetworkResponse(NetworkResponse response) {
            boolean result = false;
            String message = "Error logging in";
            if (response.statusCode < 400) {
                result = true;
                message = "";
            }
            WebResult wr = new WebResult(response.statusCode, message, result);
            //TODO return object wr

            return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
        }
    }
}
