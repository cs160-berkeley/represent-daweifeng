package io.dawei.represent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Random;


public class LocationActivity extends AppCompatActivity {
    public static final String EXTRA_ZIPCODE = "EXTRA_ZIP_CODE";
    public static final String EXTRA_LATITUDE = "EXTRA_LATITUDE";
    public static final String EXTRA_LONGITUDE = "EXTRA_LONGITUDE";


    private String latitude = "";
    private String longitude = "";
    private String zipCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        final LocationActivity context = this;

        final EditText ZipCodeField = findViewById(R.id.editText3);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//      Clear Radio Group checked
        ZipCodeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    radioGroup.clearCheck();
                    latitude = "";
                    longitude = "";
                } else {
                    //        Get Zip Code Input
                    Log.d("entered", String.valueOf(ZipCodeField.getText()));
                    zipCode = String.valueOf(ZipCodeField.getText());
                }

            }
        });
//        Clear editText focus
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    ZipCodeField.setText("");
                    ZipCodeField.clearFocus();
                    zipCode = "";

                    RadioButton checkedButton = findViewById(checkedId);
                    Log.d("check", String.valueOf(checkedId));
//              Get Location
                    if (checkedId == R.id.radioButtonCurrentLocation) {
                        Log.d("test","aaaaaaa");
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            Log.d("permission", "No permission");
                            ActivityCompat.requestPermissions(context,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    100);

                            return;
                        }
                        mFusedLocationClient.getLastLocation()
                                .addOnSuccessListener(context, new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        // Got last known location. In some rare situations this can be null.

                                        if (location != null) {
                                            // Logic to handle location object

                                            latitude = String.valueOf(location.getLatitude());
                                            longitude = String.valueOf(location.getLongitude());
                                            Log.d("lat", latitude);
                                            Log.d("lon", longitude);

                                        }
                                    }
                                });

                    } else if (checkedId == R.id.radioButtonRandomLocation) {
                        latitude = "";
                        longitude = "";
                        Random rand = new Random();
                        int randomValue = rand.nextInt(99999);
                        zipCode = String.valueOf(randomValue);
                        Log.d("random zip code", zipCode);
                    }

                }
            }
        });
    }
    public void sendLocation(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        if (zipCode != "") {
            intent.putExtra(EXTRA_ZIPCODE, zipCode);
            startActivity(intent);
        } else if (latitude !="" && longitude != "") {
            intent.putExtra(EXTRA_LATITUDE, latitude);
            intent.putExtra(EXTRA_LONGITUDE, longitude);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please Enter A Zip Code or Select Your Location", Toast.LENGTH_SHORT).show();
            return ;
        }
    }
}
