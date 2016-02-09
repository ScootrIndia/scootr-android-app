package com.firebug.scootr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.Utility;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

<<<<<<< HEAD
                Utility.setSharedPreference(SplashActivity.this,Constant.is_services_ON,"no");

=======
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325

                if(Utility.getSharedPreferences(SplashActivity.this, Constant.is_login).equalsIgnoreCase("yes")) {
                    Intent i = new Intent(SplashActivity.this, NavigationHome.class);
                    startActivity(i);
                    SplashActivity.this.finish();
                }else {
                    Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    SplashActivity.this.finish();
                }

            }
        }, 2000);
    }

}
