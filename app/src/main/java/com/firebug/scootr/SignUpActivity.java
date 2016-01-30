package com.firebug.scootr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.firebug.scootr.Views.MyTextView;

/**
 * Created by PremSai on 1/30/2016.
 */
public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        ((MyTextView)findViewById(R.id.btn_sign_up_merchant)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, NavigationHome.class));
            }
        });
    }
}
