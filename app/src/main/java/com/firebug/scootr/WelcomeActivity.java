package com.firebug.scootr;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.firebug.scootr.Views.MyTextView;

/**
 * Created by PremSai on 1/30/2016.
 */

public class WelcomeActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ((MyTextView)findViewById(R.id.sign_in)).setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, SignInActivity.class));
            }
        });

        ((MyTextView)findViewById(R.id.sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,SignUpActivity.class));
            }
        });

    }
}