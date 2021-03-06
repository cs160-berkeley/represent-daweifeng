package io.dawei.represent;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CDInfo {

    private String BASE_URL = "https://api.geocod.io/v1.3/";
    private String API_KEY = "ab840b0309e46f89fd40b5150aba918e05bb151";

    private String zipCode = "";
    private String longitude = "";
    private String latitude = "";
    public String address = "";
    public android.content.Context context;
    public List<Representative> representatives;
    private ListView listView;
    private TextView textViewLocation;

    CDInfo(android.content.Context context, ListView listView, TextView textViewLocation) {
        this.context = context;
        this.listView = listView;
        this.textViewLocation = textViewLocation;
    }


    public void getWithZipCode(String zipCode) {
        this.zipCode = zipCode;
        sendRequest(zipCode, false, this);
    }

    public void getWithGeo(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        String geoCode = latitude+","+longitude;
        sendRequest(geoCode, true, this);
    }

    private void sendRequest(String location, Boolean reverseGeo, final CDInfo thisObject) {
        Log.d("location", location);
        String url;
        if (!reverseGeo) {
            url = BASE_URL + "geocode?q=" + location + "&fields=cd&api_key=" + API_KEY;
        } else {
            url = BASE_URL + "reverse?q=" + location + "&fields=cd&api_key=" + API_KEY;
        }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d("response", response.toString());
                            try {
                                JSONObject result = response.getJSONArray("results").getJSONObject(0);

                                thisObject.address = result.getString("formatted_address");
                                textViewLocation.setText(thisObject.address);

                                JSONArray cds = result.getJSONObject("fields").getJSONArray("congressional_districts");
//                              Keep track of the iterated reps
                                ArrayList <String> nameRep = new ArrayList<String>(cds.length()*3);
                                List<Representative> representatives = new ArrayList<Representative>();

                                for (int i=0; i<cds.length(); i++) {
                                    JSONObject cd = cds.getJSONObject(i);
                                    JSONArray legislators = cd.getJSONArray("current_legislators");
                                    for (int j=0; j<legislators.length(); j++) {
                                        JSONObject legislator = legislators.getJSONObject(j);
                                        String name = legislator.getJSONObject("bio").getString("first_name") +" "+ legislator.getJSONObject("bio").getString("last_name");
                                        String party = legislator.getJSONObject("bio").getString("party");
                                        String phoneNumber = legislator.getJSONObject("contact").getString("phone");
                                        String url = legislator.getJSONObject("contact").getString("url");
                                        String contact_form = legislator.getJSONObject("contact").getString("contact_form");
                                        String bio_guide_id = legislator.getJSONObject("references").getString("bioguide_id");


                                        if (nameRep.contains(name)) {
                                            continue;
                                        }
                                        Representative representative = new Representative(name, party, phoneNumber, url, contact_form, bio_guide_id, thisObject.getAddress());
                                        representatives.add(representative);
                                        nameRep.add(name);
                                    }
                                }

                                thisObject.representatives = representatives;
                                RepresentativeListAdapter adapter = new
                                        RepresentativeListAdapter(thisObject.context,R.layout.representative_list_item,representatives);

                                thisObject.listView.setAdapter(adapter);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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

    public String getAddress() {
        return this.address;
    }



}
