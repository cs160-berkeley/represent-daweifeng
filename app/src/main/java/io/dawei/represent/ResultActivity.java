package io.dawei.represent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private String zipCode = "";
    private String longitude = "";
    private String latitude = "";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        if (intent.hasExtra(LocationActivity.EXTRA_ZIPCODE)) {
            zipCode = intent.getStringExtra(LocationActivity.EXTRA_ZIPCODE);
            listView = (ListView) findViewById(R.id.listView);
            CDInfo cdInfo = new CDInfo(this, listView);
            cdInfo.getWithZipCode(zipCode);

        } else {
            latitude = intent.getStringExtra(LocationActivity.EXTRA_LATITUDE);
            longitude = intent.getStringExtra(LocationActivity.EXTRA_LONGITUDE);
        }



    }










    private class AsyncListViewLoader extends AsyncTask<String, Void, List<Representative>> {

        @Override
        protected List<Representative> doInBackground(String... strings) {
            return null;
        }
    }

    public class Representative implements Serializable {

    }
}
