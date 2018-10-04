package io.dawei.represent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    public static String EXTRA_NAME = "EXTRA_NAME";
    public static String EXTRA_PARTY = "EXTRA_PARTY";
    public static String EXTRA_ID = "EXTRA_ID";

    private String zipCode = "";
    private String longitude = "";
    private String latitude = "";
    ListView listView;
    TextView textViewLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        if (intent.hasExtra(LocationActivity.EXTRA_ZIPCODE)) {
            zipCode = intent.getStringExtra(LocationActivity.EXTRA_ZIPCODE);
            listView = (ListView) findViewById(R.id.listView);
            textViewLocation = findViewById(R.id.textViewLocation);
            CDInfo cdInfo = new CDInfo(this, listView, textViewLocation);

            cdInfo.getWithZipCode(zipCode);

        } else {
            latitude = intent.getStringExtra(LocationActivity.EXTRA_LATITUDE);
            longitude = intent.getStringExtra(LocationActivity.EXTRA_LONGITUDE);
            listView = (ListView) findViewById(R.id.listView);
            textViewLocation = findViewById(R.id.textViewLocation);
            CDInfo cdInfo = new CDInfo(this, listView, textViewLocation);

            cdInfo.getWithGeo(latitude,longitude);
        }



    }

}
