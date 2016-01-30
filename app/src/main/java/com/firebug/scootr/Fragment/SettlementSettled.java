package com.firebug.scootr.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebug.scootr.R;

/**
 * Created by PremSai on 1/30/2016.
 */
public class SettlementSettled extends Fragment{


    View view;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_settlement_settled, container, false);
        context = this.getActivity();
        init();
        return view;

    }

    public  void init(){


    }
}
