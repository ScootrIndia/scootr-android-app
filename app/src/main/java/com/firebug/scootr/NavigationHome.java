package com.firebug.scootr;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.firebug.scootr.Views.MyTextView;
import com.firebug.scootr.utility.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NavigationHome extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    boolean go_offline;

    private boolean isNetworkEnabled = false;
    private LocationManager locationManager;
    private boolean isGPSEnabled = false;
    double lat = 0, lng = 0;
    Context context;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_home_activity);
        context = this;
        init();

    }

    public void init() {
        drawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));

        ((ImageView) findViewById(R.id.menu)).setOnClickListener(this);

        ((LinearLayout) findViewById(R.id.order_layout)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.settlement_layout)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.setting_layout)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.support_layout)).setOnClickListener(this);
        ((RelativeLayout) findViewById(R.id.go_offline_layout)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.go_online)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.confirm)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.rider_end_delivery)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.state_ride)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.merchant_end_ride)).setOnClickListener(this);

        gps = new GPSTracker(context);


// googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//

    }

    @Override
    protected void onResume() {
        super.onResume();
        showMyLocationMap();

    }

    public void showMyLocationMap() {

        if (Getlocation()) {

            GoogleMap googleMap;
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_google)).getMap();

            try {
                final LatLng TutorialsPoint = new LatLng(lat, lng);
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Scootr Point 1")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.scootr_map));
                googleMap.getUiSettings().setZoomGesturesEnabled(true);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 10);
                googleMap.animateCamera(cameraUpdate);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    public void openLeft() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
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
            gps.getLocation();
            lat = (new GPSTracker(context).getLatitude());
            lng = (new GPSTracker(context).getLongitude());
            System.out.println("value of lat and long " + lat + ":" + lng);

            return true;


        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.settlement_layout) {
            openLeft();
            startActivity(new Intent(NavigationHome.this, SettlementActivity.class));
        } else if (v.getId() == R.id.go_offline_layout) {
            openLeft();
            if (go_offline) {
                go_offline = false;
                ((RelativeLayout) findViewById(R.id.offline_scootr)).setVisibility(View.GONE);
                ((RelativeLayout) findViewById(R.id.main_layout)).setVisibility(View.VISIBLE);

            } else {
                go_offline = true;
                ((RelativeLayout) findViewById(R.id.offline_scootr)).setVisibility(View.VISIBLE);
                ((RelativeLayout) findViewById(R.id.main_layout)).setVisibility(View.GONE);
            }
        } else if (v.getId() == R.id.go_online) {
            go_offline = false;
            ((RelativeLayout) findViewById(R.id.offline_scootr)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.main_layout)).setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.confirm) {
            ((RelativeLayout) findViewById(R.id.request_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.arrived_layout)).setVisibility(View.VISIBLE);

        }else if (v.getId() == R.id.rider_end_delivery) {
            ((RelativeLayout) findViewById(R.id.arrived_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.start_ride_layout)).setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.state_ride) {
            ((RelativeLayout) findViewById(R.id.start_ride_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.merchant_end_ride) {
            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.VISIBLE);

        }
//        else if (v.getId() == R.id.merchant_end_ride) {
//            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.GONE);
//            ((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.VISIBLE);
//
//        }
        else {
            openLeft();
        }
    }
}
