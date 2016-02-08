package com.firebug.scootr;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebug.scootr.Views.MyTextView;
import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.DialogMessage;
import com.firebug.scootr.utility.GPSTracker;
import com.firebug.scootr.utility.RestClient;
import com.firebug.scootr.utility.Utility;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EndDeliveryActivity extends AppCompatActivity {

    MyTextView ed, sum;
    ProgressDialog prgDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_delivery);
        ed = (MyTextView) findViewById(R.id.merchant_end_ride);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                new EndRequest().execute();


                Intent ar = new Intent(EndDeliveryActivity.this, SummaryActivity.class);
                startActivity(ar);
            }
        });


        context = this;

        prgDialog=new ProgressDialog(context);
        prgDialog.setMessage("Please wait.. ");
        prgDialog.setCancelable(false);


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


    public class EndRequest extends AsyncTask<String, String, String> {
        int code;


        @Override
        protected void onPreExecute() {

            prgDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String result = null;
            String url = null;


//            https://cryptic-shelf-9191.herokuapp.com/api/companies/1/bank_account/1

            url = Constant.BASE_URL_RIDE_S;

            RestClient client = new RestClient(url);

//            ride[customer_name]

            client.AddParam("ride[status]","done" );
            client.AddParam("ride[rider_id]", Utility.getSharedPreferences(context, Constant.Uid));
            client.AddHeader("access-token", Utility.getSharedPreferences(context, Constant.Access_Token));
            client.AddHeader("client", Utility.getSharedPreferences(context, Constant.Client));
            client.AddHeader("expiry", Utility.getSharedPreferences(context, Constant.Expiry));
            client.AddHeader("token-type", Utility.getSharedPreferences(context, Constant.Token_Type));
            client.AddHeader("uid", Utility.getSharedPreferences(context, Constant.Uid));

            try {

                client.ExecuteGet();
                result = client.getResponse();
                code = client.getResponseCode();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }


        @Override
        protected void onPostExecute(String s) {
            prgDialog.dismiss();

            if (s!=null) {
                try {
                    Log.e("ssssss", "" + code);

                    if(code==200||code==201){

                        Intent ar = new Intent(EndDeliveryActivity.this, SummaryActivity.class);
                        startActivity(ar);

                    }else if(code==422){
                        DialogMessage.showPopupMessage("Something went wrong , Please try again!", context);
                    }else{
                        DialogMessage.showPopupMessage("Something went wrong , Please try again!", context);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    DialogMessage.showPopupMessage("Please check your internet connection", context);
                }
            } else {
                DialogMessage.showPopupMessage("Please check your internet connection", context);
//				Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }

    }

}
