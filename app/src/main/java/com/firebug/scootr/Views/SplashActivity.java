package com.firebug.scootr.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.firebug.scootr.NavigationHome;
import com.firebug.scootr.R;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your Application's NavigationHome Activity

              Intent i = new Intent(SplashActivity.this, NavigationHome.class);
             //    Intent i = new Intent(SplashActivity.this, TestMyList.class);

              //  Intent i = new Intent(SplashActivity.this, TestMyMainPage.class);
                startActivity(i);
                // close this activity
                SplashActivity.this.finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
    }

}
