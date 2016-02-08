package com.firebug.scootr.Services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.GPSTracker;
import com.firebug.scootr.utility.RestClient;
import com.firebug.scootr.utility.Utility;

public class UpdateRiderLocation extends Service {
    private static final String TAG = UpdateRiderLocation.class.getSimpleName();
    //private Context appContext;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //appContext=getApplicationContext();

        Log.e("services v","onBind");

        new updateLocation().execute();

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("services v","onBind");


        return super.onStartCommand(intent, flags, startId);
    }

    public class updateLocation extends AsyncTask<String, String, String> {

        int code;
        String email;


        @Override
        protected String doInBackground(String... params) {

            Utility.setSharedPreference(getApplicationContext(),Constant.is_services_ON,"yes");

            String result = null;
            String url = null;


            url = Constant.BASE_URL_RIDE + "positions";

            RestClient client = new RestClient(url);
        try {
            new GPSTracker(getApplicationContext()).getLocation();
            client.AddParam("rider_position[lat]",new GPSTracker(getApplicationContext()).getLatitude()+"" );
            client.AddParam("rider_position[long]",new GPSTracker(getApplicationContext()).getLongitude()+"" );
            client.AddParam("rider_position[rider_id]", Utility.getSharedPreferences(getApplicationContext(), Constant.USER_ID));


            client.AddHeader("access-token", Utility.getSharedPreferences(getApplicationContext(), Constant.Access_Token));
            client.AddHeader("client", Utility.getSharedPreferences(getApplicationContext(), Constant.Client));
            client.AddHeader("expiry", Utility.getSharedPreferences(getApplicationContext(), Constant.Expiry));
            client.AddHeader("token-type", Utility.getSharedPreferences(getApplicationContext(), Constant.Token_Type));
            client.AddHeader("uid", Utility.getSharedPreferences(getApplicationContext(), Constant.Uid));

        }catch (Exception e){e.printStackTrace();}

            try {

                client.ExecutePost();
                result = client.getResponse();
                code = client.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }


        @Override
        protected void onPostExecute(String s) {

                try {


                    Log.e("data update",s+"  data");

                    CountDownTimer cdt = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {


                        }

                        @Override
                        public void onFinish() {
                            new updateLocation().execute();
                        }
                    };

                    cdt.start();

                } catch (Exception e) {
                    e.printStackTrace();

                }
            super.onPostExecute(s);
        }

    }

}
