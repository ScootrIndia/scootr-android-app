package com.firebug.scootr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.firebug.scootr.Fragment.SettlementPendding;
import com.firebug.scootr.Fragment.SettlementSettled;
import com.firebug.scootr.Layout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PremSai on 1/30/2016.
 */
public class SettlementActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);


        ((ImageView)findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initTab();

    }

    public void initTab(){

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter((this).getSupportFragmentManager());
        pagerAdapter.addFragment(new SettlementPendding(),"Pending");
        pagerAdapter.addFragment(new SettlementSettled(), "Settled");
        viewPager.setAdapter(pagerAdapter);

        SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.mydarkColor_new);
            }
        });
        tabs.setViewPager(viewPager);

    }
    static class PagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {

            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("position", position + "");
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override

        public long getItemId(int position) {
            Log.e("pos",position+"");
            return super.getItemId(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }




}
