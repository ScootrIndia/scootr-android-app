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

public class SummaryActivity extends AppCompatActivity {

    MyTextView ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        ed = (MyTextView) findViewById(R.id.btn_close);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent = new Intent(new Intent(SummaryActivity.this, NavigationHome.class));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

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

            GoogleMap googleMap,googleMap_imageMap2;
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_google)).getMap();
            googleMap_imageMap2 = ((MapFragment) getFragmentManager().findFragmentById(R.id.imageMap2)).getMap();
            try {
                final LatLng TutorialsPoint = new LatLng(lat, lng);
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Scootr Point 1")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.scootr_map));
                googleMap.getUiSettings().setZoomGesturesEnabled(true);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 15);
                googleMap.animateCamera(cameraUpdate);
            } catch (Exception e) {
                e.printStackTrace();
            }try {
                final LatLng TutorialsPoint = new LatLng(lat, lng);
                googleMap_imageMap2.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap_imageMap2.addMarker(new MarkerOptions().position(TutorialsPoint).title("Scootr Point 1")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.scootr_map));
                googleMap_imageMap2.getUiSettings().setZoomGesturesEnabled(true);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 15);
                googleMap_imageMap2.animateCamera(cameraUpdate);
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
