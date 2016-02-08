package com.firebug.scootr;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebug.scootr.Views.MyTextView;
import com.firebug.scootr.utility.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EndDelivery2 extends AppCompatActivity {


    MyTextView ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_delevery2);

        ed = (MyTextView) findViewById(R.id.merchant_end_ride2);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ar = new Intent(EndDelivery2.this, SummaryActivity.class);
                startActivity(ar);
            }
        });

        context = this;
        showMyLocationMap();
    }

    private boolean isNetworkEnabled = false;

    private LocationManager locationManager;
    private boolean isGPSEnabled = false;
    double lat = 0, lng = 0;
    Context context;

    public void showMyLocationMap() {

        if (Getlocation()) {

            GoogleMap googleMap;
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_google)).getMap();
            try {
                final LatLng TutorialsPoint = new LatLng(lat, lng);
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Scootr Point 1")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.scootr_map));
                googleMap.getUiSettings().setZoomGesturesEnabled(true);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 15);
                googleMap.animateCamera(cameraUpdate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public boolean Getlocation() {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSEnabled == false && isNetworkEnabled == false) {
            new GPSTracker(context).showSettingsAlert();
            return false;
        } else {
            new GPSTracker(context).getLocation();
            lat = (new GPSTracker(context).getLatitude());
            lng = (new GPSTracker(context).getLongitude());
            System.out.println("value of lat and long " + lat + ":" + lng);

            return true;


        }
    }
}
