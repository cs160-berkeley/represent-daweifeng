package io.dawei.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        TextView viewName = findViewById(R.id.textViewDetailName);
        TextView viewParty = findViewById(R.id.textViewDetailParty);
        final TextView viewTitle = findViewById(R.id.textViewPosition);
        ImageView imageView = findViewById(R.id.imageView5);

        viewName.setText(intent.getStringExtra(ResultActivity.EXTRA_NAME));
        viewParty.setText(intent.getStringExtra(ResultActivity.EXTRA_PARTY));
        if (intent.getStringExtra(ResultActivity.EXTRA_PARTY).equals("Republican")) {
            viewParty.setBackgroundResource(R.drawable.party_bg_red);
        }

        String id = intent.getStringExtra(ResultActivity.EXTRA_ID);

        String imageUrl = "https://theunitedstates.io/images/congress/225x275/" + id + ".jpg";
        Picasso.get().load(imageUrl).resize(250, 250).centerCrop().transform(new CircleTransform(600,0)).into(imageView);


//        Get Committees
        String rep_url = "https://api.propublica.org/congress/v1/members/" + id + ".json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, rep_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");
                            JSONObject rep = results.getJSONObject(0);
                            JSONObject role = rep.getJSONArray("roles").getJSONObject(0);
                            JSONArray committees = role.getJSONArray("committees");
                            String title = role.getString("title");

                            List<String> committeesName = new ArrayList<String>();
                            for (int i=0; i<committees.length(); i++) {
                                JSONObject committee = committees.getJSONObject(i);
                                committeesName.add(committee.getString("name"));
                            }

//                            Set title
                            viewTitle.setText(title);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                })
                {
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        HashMap headers = new HashMap();
                        headers.put("Content-Type", "application/json");
                        headers.put("X-API-Key", "3BU2veE5GRfv156nVHHSLeNEci9MkVMKY2bWtZXI");
                        return headers;
                    }
                };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }
}
