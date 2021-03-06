package com.firebug.scootr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebug.scootr.Views.*;
import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.Utility;

public class Settings extends AppCompatActivity {


    MyTextView editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        editProfile = (MyTextView) findViewById(R.id.profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii = new Intent(Settings.this, EditProfileActivity.class);
                startActivity(ii);
            }
        });


        ((ImageView)findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ((MyTextView)findViewById(R.id.sign_out)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.setSharedPreference(Settings.this, Constant.is_login,"no");
                Intent  intent = new Intent(new Intent(Settings.this, WelcomeActivity.class));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }


}
