package com.firebug.scootr;

<<<<<<< HEAD
import android.app.ProgressDialog;
=======
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.firebug.scootr.Services.UpdateRiderLocation;
import com.firebug.scootr.Views.MyTextView;
<<<<<<< HEAD
import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.DialogMessage;
=======
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
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
<<<<<<< HEAD
import com.google.android.gms.vision.barcode.Barcode;
import com.rey.material.widget.ProgressView;

=======

import com.google.android.gms.vision.barcode.Barcode;

>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

<<<<<<< HEAD
=======

>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
public class NavigationHome extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    boolean go_offline;

    private boolean isNetworkEnabled = false;
    private LocationManager locationManager;
    private boolean isGPSEnabled = false;
<<<<<<< HEAD
    double lat = 0, lng = 0;
    Context context;

    MyTextView tt, ar, ed, sum;
=======
    double lat = 0.0, lng =0.0;
    Context context;
    GPSTracker gps;
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_home_activity);
        context = this;
<<<<<<< HEAD

=======
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
        init();


    }

    public void init() {
<<<<<<< HEAD

        drawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));

        ((ImageView) findViewById(R.id.menu)).setOnClickListener(this);

        ((LinearLayout) findViewById(R.id.order_layout)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.settlement_layout)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.setting_layout)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.support_layout)).setOnClickListener(this);
        ((RelativeLayout) findViewById(R.id.go_offline_layout)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.go_online)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.confirm)).setOnClickListener(this);
        ((MyTextView) findViewById(R.id.cancel)).setOnClickListener(this);



        if(Utility.getSharedPreferences(context, Constant.is_services_ON).equalsIgnoreCase("no")){
            startService(new Intent(context, UpdateRiderLocation.class));
        }

=======
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


>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
// googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//
<<<<<<< HEAD
        prgDialog=new ProgressDialog(context);
        prgDialog.setMessage("Please wait.. \n We are getting Request");
        prgDialog.setCancelable(false);

    }

    ProgressDialog prgDialog;

    @Override
    protected void onResume() {

        showMyLocationMap();

        if(Utility.getSharedPreferences(context,Constant.ride_done_status).equalsIgnoreCase("0")||Utility.getSharedPreferences(context,Constant.ride_done_status).equalsIgnoreCase("")){
            prgDialog.dismiss();
            new getRequest().execute();
        }
        super.onResume();

    }
=======

    }

    @Override
    protected void onResume() {
        super.onResume();
        showMyLocationMap();

    }

>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325

    public void showMyLocationMap() {

        if (Getlocation()) {

<<<<<<< HEAD
            GoogleMap googleMap;
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_google)).getMap();
            try {
                final LatLng TutorialsPoint = new LatLng(lat,lng);
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

    public void openLeft() {

=======
            public void showMyLocationMap () {

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


                GoogleMap googleMap;
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_google)).getMap();
                try {
                    final LatLng TutorialsPoint = new LatLng(lat, lng);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Scootr Point 1")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.scootr_map));
                    googleMap.getUiSettings().setZoomGesturesEnabled(true);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(TutorialsPoint, 12);
                    googleMap.animateCamera(cameraUpdate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    public void openLeft() {
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }


<<<<<<< HEAD


    @Override
    public void onClick(View v) {
=======
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

>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
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
<<<<<<< HEAD
        } else if (v.getId() == R.id.rider_end_delivery) {

            /*((RelativeLayout) findViewById(R.id.arrived_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.start_ride_layout)).setVisibility(View.VISIBLE);
*/
        } else if (v.getId() == R.id.state_ride) {
            ((RelativeLayout) findViewById(R.id.start_ride_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.VISIBLE);
        }
        // need  to sumup
        else if (v.getId() == R.id.merchant_end_ride) {
           /* ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.VISIBLE);*/

        } else if (v.getId() == R.id.merchant_end_ride) {

            /*((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.request_layout)).setVisibility(View.VISIBLE);
*/
        } else if (v.getId() == R.id.merchant_end_ride) {

            /*me uncomment*/

=======

        } else if (v.getId() == R.id.rider_end_delivery) {
        } else if (v.getId() == R.id.confirm) {
            ((RelativeLayout) findViewById(R.id.request_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.arrived_layout)).setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.rider_end_delivery) {

            ((RelativeLayout) findViewById(R.id.arrived_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.start_ride_layout)).setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.state_ride) {
            ((RelativeLayout) findViewById(R.id.start_ride_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.VISIBLE);

        }
        // need  to sumup
        else if (v.getId() == R.id.merchant_end_ride) {
            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.merchant_end_ride) {
            ((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.request_layout)).setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.merchant_end_ride) {
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.GONE);
            ((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.VISIBLE);

        } else if (v.getId() == R.id.setting_layout) {
            openLeft();
            startActivity(new Intent(NavigationHome.this, Settings.class));
<<<<<<< HEAD
        } else if (v.getId() == R.id.confirm) {

//            Intent end = new Intent(NavigationHome.this, EndDeliveryActivity.class);
            Intent end = new Intent(NavigationHome.this, StartDeleveryActivity.class);
            startActivity(end);
//            new ConfirmRide().execute();

        } else if (v.getId() == R.id.cancel) {
            ((RelativeLayout)findViewById(R.id.request_layout)).setVisibility(View.GONE);
//            new CancelRide().execute();

=======
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
        } else {
            openLeft();
        }
    }


<<<<<<< HEAD
    public class getRequest extends AsyncTask<String, String, String> {
        int code;


        @Override
        protected void onPreExecute() {
            prgDialog.setMessage("Please wait.. \n We are getting Request");
            prgDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String result = null;
            String url = null;


//            https://cryptic-shelf-9191.herokuapp.com/api/companies/1/bank_account/1

            url =Constant.BASE_URL_RIDE_S + "pending";

            RestClient client = new RestClient(url);

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
                        JSONArray json = new JSONArray(s);

                        if(json.length()>0){
                            ((RelativeLayout)findViewById(R.id.request_layout)).setVisibility(View.VISIBLE);
                        }else {
                            ((RelativeLayout)findViewById(R.id.request_layout)).setVisibility(View.VISIBLE);
                            DialogMessage.showPopupMessage("Opps no pending ride request found!", context);
                        }
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


       public class ConfirmRide extends AsyncTask<String, String, String> {
        int code;


        @Override
        protected void onPreExecute() {
            prgDialog.setMessage("Please wait...");
            prgDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String result = null;
            String url = null;


//            https://cryptic-shelf-9191.herokuapp.com/api/companies/1/bank_account/1

            url =Constant.BASE_URL_RIDE_S;

            RestClient client = new RestClient(url);

//            ride[customer_name]

            client.AddParam("ride[status]","picking_up" );
            client.AddParam("ride[rider_id]",Utility.getSharedPreferences(context,Constant.Uid));

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
                        Intent end = new Intent(NavigationHome.this, StartDeleveryActivity.class);
                        startActivity(end);
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

    public class CancelRide extends AsyncTask<String, String, String> {
        int code;


        @Override
        protected void onPreExecute() {

            prgDialog.setMessage("Please wait.. ");
            prgDialog.show();

=======
    /*public void startService() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //Call the method that contacts the server and
                //adds the coordinates to a list. Get those values from the list.
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.kre8tiveinspired.com/android/serverFile.php");
                JSONObject json = new JSONObject();

                try {
                    // JSON data:
                    JSONArray postjson = new JSONArray();
                    postjson.put(json);
                    // Execute HTTP Post Request
                    System.out.print(json);
                    HttpResponse response = httpclient.execute(httppost);
                    // for JSON:
                    if (response != null) {
                        InputStream is = response.getEntity().getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        String jsonStr = sb.toString();
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        // grabbing the menu object
                        String longitudecord = jsonObj.getString("lon");
                        String latitudecord = jsonObj.getString("lat");
                        lat = latitudecord;
                        loncord = longitudecord;
                    }

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    public void run() {

                        double aDouble = Double.parseDouble(loncord);
                        double bDouble = Double.parseDouble(latcord);

                        int lat = (int) (aDouble * 1E6);
                        int lng = (int) (bDouble * 1E6);
                        Barcode.GeoPoint point = new Barcode.GeoPoint(lat, lng);
                        createMarker();
                        mapController.animateTo(point); // mapController.setCenter(point);
                        //add the overlays to the map
                        // and call invalidate for the mapview.
                        mapView.invalidate();

                    }
                });
            }

        }, 0, 1000); // Here im calling the timer every 10 seconds




        timer=new Timer();  // Refresh map via timer task.
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                //Call the method that contacts the server and
                //adds the coordinates to a list. Get those values from the list.
                Handler handler = new Handler();
                handler.post(new Runnable(){
                    public void run(){
                        //add the overlays to the map
                        // and call invalidate for the mapview.
                    }
                });
            }

        }, 0, 10000); // Here im calling the timer every 10 seconds

=======
        }
//        else if (v.getId() == R.id.merchant_end_ride) {
//            ((RelativeLayout) findViewById(R.id.merchant_end_delivery_layout)).setVisibility(View.GONE);
//            ((RelativeLayout) findViewById(R.id.summary_layout)).setVisibility(View.VISIBLE);
//
//        }
        else {
            openLeft();
        }
>>>>>>> bec269e0751e446bc61478725c8214a2a32a4d0c
    }
*/



    class GetLocation extends AsyncTask<Void, Void, Void>{

        String weserviceResposnse;
        @Override
        protected Void doInBackground(Void... params) {
         //   Utility  ui=new Utility();
            RestClient restClient=new RestClient();
            weserviceResposnse=restClient.ExecutePostJSON().;



            return null;
        }

        @Override
        protected void onPreExecute() {
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
            super.onPreExecute();
        }

        @Override
<<<<<<< HEAD
        protected String doInBackground(String... params) {

            String result = null;
            String url = null;


//            https://cryptic-shelf-9191.herokuapp.com/api/companies/1/bank_account/1

            url =Constant.BASE_URL_RIDE_S;

            RestClient client = new RestClient(url);

//            ride[customer_name]

            client.AddParam("ride[status]","canceled" );
            client.AddParam("ride[rider_id]",Utility.getSharedPreferences(context,Constant.Uid));
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
                        ((RelativeLayout)findViewById(R.id.request_layout)).setVisibility(View.GONE);
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
=======
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
        }

    }

<<<<<<< HEAD

=======
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
}