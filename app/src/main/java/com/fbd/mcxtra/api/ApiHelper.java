package com.fbd.mcxtra.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

/**
 * Created by Gourav on 09-02-2017.
 */

public class ApiHelper {
    private static ApiHelper mInstance;
    private RequestQueue mRequestQueue;
    Context context;

    ApiHelper(Context context) {
        this.context = context;
    }

    public static synchronized ApiHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ApiHelper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void doGetRequest(String url, final HashMap<String, String> headers, final ApiCallback callback) {
        Log.d("url", "" + url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("onResponse", "" + response);
                        callback.onResponse(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("onErrorResponse", "" + error.getMessage());
                        callback.onError("Server error");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers != null) {
                    return headers;
                } else {
                    return super.getHeaders();
                }
            }

        };
        addToRequestQueue(jsObjRequest);
    }

    public void doPostRequest(String url, final HashMap<String, String> headers, final JSONObject jsonObject, final ApiCallback callback) {
        Log.d("url", "" + url);
        Log.d("url", "" + jsonObject.toString());
        url = url.replace(" ", "%20");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("onResponse", "" + response);
                        if (response != null) {
                            try {
                                if (response.has("error") && response.getBoolean("error")) {
                                    try {
                                        callback.onError(response.getString("message"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else
                                    callback.onResponse(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            callback.onError("Null");
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("onErrorResponse", "" + error.getMessage());
                        if (error instanceof TimeoutError) {
                            callback.onError("Timeout bad network");
                        } else
                            callback.onError(error.getMessage());
                    }

                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers != null) {
                    return headers;
                } else {
                    return super.getHeaders();
                }
            }
        };
        try {
            Log.d("headers", "" + jsObjRequest.getHeaders().toString());
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
        addToRequestQueue(jsObjRequest);
    }

}
