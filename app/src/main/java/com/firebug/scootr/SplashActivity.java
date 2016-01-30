package com.firebug.scootr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              Intent i = new Intent(SplashActivity.this,WelcomeActivity.class);
                startActivity(i);
                SplashActivity.this.finish();

            }
        }, 2000);
    }

}
