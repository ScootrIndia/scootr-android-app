package com.firebug.scootr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.TextView;

import com.firebug.scootr.Views.*;
import com.firebug.scootr.utility.Constant;
import com.firebug.scootr.utility.Utility;

public class Settings extends AppCompatActivity {


    MyTextView editProfile;
=======
import android.widget.TextView;

public class Settings extends AppCompatActivity {


    Button editProfile;
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
<<<<<<< HEAD
        editProfile = (MyTextView) findViewById(R.id.profile);
=======
        editProfile = (Button) findViewById(R.id.edit_prfile);
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii = new Intent(Settings.this, EditProfileActivity.class);
                startActivity(ii);
            }
        });


<<<<<<< HEAD
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


=======
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent ii = new Intent(Settings.this, NavigationHome.class);
        startActivity(ii);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
>>>>>>> 14c30558053d3c5a75e0ec0e57f6c697ef356325
}
