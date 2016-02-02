package com.firebug.scootr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import location.diraction.GetDirection;
import network.JSONParser;
import utlites.UrlForWS;

public class LiveTrackingDriverActivity extends AppCompatActivity {

    private static GoogleMap mMap;
    private static GetDirection gd;
    private static Document mDoc;

    Context mContext;

    private static String CTN;

    private static LatLng driverLatLng, driverOldLatLng, pickupLatLng, destinationLatLng;
    private static Marker driverPosMarker, pickupPosMarker, destinationPosMarker;
    private static Polyline pathBetweenDriverAndPickUp, pathBetweenDriverAndDestination;

    TextView driverName, driverPhone;

    public static boolean liveTrackingAllowed, driverReachedPickUpLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tracking_driver);

        driverName = (TextView) findViewById(R.id.driver_name_textView);
        driverPhone = (TextView) findViewById(R.id.driver_phone_textView);

        mContext = this;
        gd = new GetDirection(mContext);
        gd.setOnDirectionResponseListener(onDirectionResponseListener);

        Intent driverDetails = getIntent();
        CTN = driverDetails.getStringExtra("CTN");

        try {
            // Loading map
            initilizeMap(driverDetails);

        } catch (Exception e) {
            e.printStackTrace();
        }

        new GetLiveTaxiLocationTask().execute();
    }


    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap(Intent driverDetails) {
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map_live_tracking_driver)).getMap();

            // check if map is created successfully or not
            if (mMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            } else {
                setUpMap(driverDetails);
            }
        }
    }


    private void setUpMap(Intent driverDetails) {

        liveTrackingAllowed = true;
        driverReachedPickUpLoc = false;

        driverName.setText(driverDetails.getStringExtra("driverName"));
        driverPhone.setText(driverDetails.getStringExtra("driverMobile"));

        driverLatLng = new LatLng(
                Double.parseDouble(driverDetails.getStringExtra("driverLati")),
                Double.parseDouble(driverDetails.getStringExtra("driverLong"))
        );
        pickupLatLng = new LatLng(
                Double.parseDouble(driverDetails.getStringExtra("pickupLati")),
                Double.parseDouble(driverDetails.getStringExtra("pickupLong"))
        );

        destinationLatLng = new LatLng(
                Double.parseDouble(driverDetails.getStringExtra("destiLati")),
                Double.parseDouble(driverDetails.getStringExtra("destiLong"))
        );

        showPickUpMarker();
        updateDriverMarker();
        drawPathBetweenDriverAndPickUp();
    }

    GetDirection.OnDirectionResponseListener onDirectionResponseListener = new GetDirection.OnDirectionResponseListener() {

        public void onResponse(String status, Document doc,
                               GetDirection gd) {
            mDoc = doc;
            if(liveTrackingAllowed) {
                if (pickupLatLng.equals(gd.getEndLatLng())&&!driverReachedPickUpLoc) {
                    if (pathBetweenDriverAndPickUp != null)
                        pathBetweenDriverAndPickUp.remove();
                    pathBetweenDriverAndPickUp = mMap.addPolyline(gd.getPolyline(doc, 3, Color.RED));
                }

                if (destinationLatLng.equals(gd.getEndLatLng())&&driverReachedPickUpLoc) {
                    if (pathBetweenDriverAndDestination != null)
                        pathBetweenDriverAndDestination.remove();
                    pathBetweenDriverAndDestination = mMap.addPolyline(gd.getPolyline(doc, 3, Color.RED));
                }

                if (driverLatLng.equals(gd.getEndLatLng())) {
                    mMap.addPolyline(gd.getPolyline(doc, 5, Color.BLUE));
                }
            }
//            mMap.addPolyline(gd.getPolyline(doc, 3, Color.RED));
//            String totalDistance = gd.getTotalDistanceText(mDoc);
//            String totalDuration = gd.getTotalDurationText(mDoc);
//            showToast("Distance : " + totalDistance + " Total duration : "+totalDuration);
        }
    };

    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }


    private static void updateDriverMarker() {
        if (driverPosMarker != null)
            driverPosMarker.remove();
        driverPosMarker = mMap.addMarker(new MarkerOptions()
                .position(driverLatLng)
                .title("Driver")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(driverLatLng, 15);
        mMap.animateCamera(cameraUpdate);

    }

    private static void showPickUpMarker() {
        if (pickupPosMarker != null)
            pickupPosMarker.remove();
        pickupPosMarker = mMap.addMarker(new MarkerOptions()
                .position(pickupLatLng)
                .title("Pick up point")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    private static void showDestinationMarker() {
        if (destinationPosMarker != null)
            destinationPosMarker.remove();
        destinationPosMarker = mMap.addMarker(new MarkerOptions()
                .position(destinationLatLng)
                .title("Destination")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }

    private static void updateDriverLocationOnMap() {
        updateDriverMarker();
        drawPathBetweenDriverAndPickUp();
        drawPathBetweenDriverAndDestination();
        drawPathBetweenOldDriverPosAndCurrentDriverPos();
    }

    private static void drawPathBetweenDriverAndPickUp() {
        gd.setLogging(true);
        gd.request(driverLatLng, pickupLatLng,
                GetDirection.MODE_DRIVING);
    }

    private static void drawPathBetweenDriverAndDestination() {
        gd.setLogging(true);
        gd.request(driverLatLng, destinationLatLng,
                GetDirection.MODE_DRIVING);
    }

    private static void drawPathBetweenOldDriverPosAndCurrentDriverPos() {
        gd.setLogging(true);
        gd.request(driverOldLatLng, driverLatLng,
                GetDirection.MODE_DRIVING);
    }

    private class GetLiveTaxiLocationTask extends
            AsyncTask<Void, Void, String> {
        private JSONParser jsonParser = null;
        private final String TAG = GetLiveTaxiLocationTask.class.getSimpleName();
        private String urlForDriverLiveLocation = UrlForWS.DRIVER_LIVE_LOCATION_SENDER;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //showToast("Getting Live Taxi Location");

            if(jsonParser == null)
                jsonParser = new JSONParser();
        }

        @Override
        protected String doInBackground(Void... param) {
            List<NameValuePair> params = getCustomerTripRequestNameValuePairs();
            String result = null;
            try{
                Log.e(TAG, urlForDriverLiveLocation);
                JSONObject jsonObject = jsonParser.makeHttpRequest(urlForDriverLiveLocation, "POST", params);
                result = jsonObject.toString();
            } catch (Exception e) {
                Log.e(TAG,e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(TAG,"Result Taxi Location Request : "+result);
            if(result!=null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String response = jsonObject.getString("response");
                    if (response.equals("true")) {
                        JSONObject driverLocObj = jsonObject.getJSONObject("details");
                        LatLng tempLoc= new LatLng(
                                Double.parseDouble(driverLocObj.getString("driverLati")),
                                Double.parseDouble(driverLocObj.getString("driverLong"))
                        );
                        if(!tempLoc.equals(driverLatLng)) {
                            driverOldLatLng = driverLatLng;
                            driverLatLng = tempLoc;
                            updateDriverLocationOnMap();
                        }
                        String message = jsonObject.getString("message");
                        Log.e(TAG,"Message from server : " + message);
                        //showToast(message);
                    } else {
                        String message = jsonObject.getString("message");
                        Log.e(TAG,"Message from server : " + message);
                        //showToast(message);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    showToast("Unable to send request, please try again later.");
                }
            }
            if(liveTrackingAllowed) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        new GetLiveTaxiLocationTask().execute();
                    }
                }, 30000);
            }
            Log.e(TAG, result);
        }

        @NonNull
        private List<NameValuePair> getCustomerTripRequestNameValuePairs() {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("unique_key", mContext.getString(R.string.app_unique_key_for_web_services)));
            params.add(new BasicNameValuePair("CTN", CTN));
            Log.e("param", params.toString());

            return params;
        }
    }


    public static void TrackDriverForDestination() {
        mMap.clear();
        liveTrackingAllowed = true;
        driverReachedPickUpLoc = true;
        updateDriverMarker();
        showPickUpMarker();
        showDestinationMarker();
        updateDriverLocationOnMap();
    }


}
