package com.example.bestlocprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationActivity extends AppCompatActivity {
    LocationManager locManager;
    LocationListener locationListener;
    TextView myLocField;
    Criteria criteria;
    String bestProviderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLocField = findViewById(R.id.myLocationField);
        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        bestProviderName = locManager.getBestProvider(criteria, true);
        //      myLocField.setText("Best loc. provider: " + bestProviderName);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                myLocField.setText("Latitude: " + location.getLatitude()
                        + "\nLongitude: " + location.getLongitude()
                        + "\nAltitude: " + location.getAltitude());
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MyLocationActivity.this,
                    "First enable Location ACCESS in settings.", Toast.LENGTH_LONG).show();
            return;
        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        //locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);

    }

    @Override
    protected void onPause() {
        super.onPause();
        locManager.removeUpdates((locationListener));
    }

    @Override
    protected void onResume() {
        super.onResume();
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;

        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
}