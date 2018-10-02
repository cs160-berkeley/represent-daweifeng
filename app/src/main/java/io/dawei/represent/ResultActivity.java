package io.dawei.represent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


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
