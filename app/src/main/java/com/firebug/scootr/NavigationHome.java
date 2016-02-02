package com.firebug.scootr;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.firebug.scootr.Views.MyTextView;

public class NavigationHome extends AppCompatActivity implements View.OnClickListener{

    DrawerLayout drawerLayout;
    boolean go_offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_home_activity);

        init();

    }

    public void init(){
        drawerLayout=((DrawerLayout)findViewById(R.id.drawer_layout));

        ((ImageView)findViewById(R.id.menu)).setOnClickListener(this);

        ((LinearLayout)findViewById(R.id.order_layout)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.settlement_layout)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.setting_layout)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.support_layout)).setOnClickListener(this);
        ((RelativeLayout)findViewById(R.id.go_offline_layout)).setOnClickListener(this);
        ((MyTextView)findViewById(R.id.go_online)).setOnClickListener(this);

    }

    public void openLeft(){
        if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
            drawerLayout.closeDrawer(Gravity.LEFT);
        }else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.settlement_layout){
            openLeft();
            startActivity(new Intent(NavigationHome.this,SettlementActivity.class));
        }else if(v.getId()==R.id.go_offline_layout){
            openLeft();
            if(go_offline) {
                go_offline=false;
                ((RelativeLayout) findViewById(R.id.offline_scootr)).setVisibility(View.GONE);
            } else {
                go_offline=true;
                ((RelativeLayout) findViewById(R.id.offline_scootr)).setVisibility(View.VISIBLE);
            }
        }else if(v.getId()==R.id.go_online){
            go_offline=false;
            ((RelativeLayout) findViewById(R.id.offline_scootr)).setVisibility(View.GONE);
        }else{
      openLeft();
    }
    }
}
