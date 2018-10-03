package io.dawei.represent;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class CDInfo {

    private String BASE_URL = "https://api.geocod.io/v1.3/";
    private String API_KEY = "ab840b0309e46f89fd40b5150aba918e05bb151";

    private String zipCode = "";
    private String longitude = "";
    private String latitude = "";
    private android.content.Context context;

    CDInfo(android.content.Context context) {
        this.context = context;
    }


    public void getWithZipCode(String zipCode) {
        this.zipCode = zipCode;
        sendRequest(zipCode, false);
    }

    private void sendRequest(String location, Boolean reverseGeo) {
        Log.d("location", location);
        if (!reverseGeo) {
            String url = BASE_URL + "geocode?q=" + location +"&fields=cd&api_key=" + API_KEY;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("response", response.toString());
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Log.d("error", error.toString());
                        }
                    });
            RequestQueue queue = Volley.newRequestQueue(this.context);
            queue.add(jsonObjectRequest);
        }
    }
}
