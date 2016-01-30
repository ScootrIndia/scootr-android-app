package com.firebug.scootr.Fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.firebug.scootr.R;

import java.util.Calendar;

/**
 * Created by win8 on 10/5/2015.
 */
public class BookScootr extends Fragment {

    View view;
    Context context;
    static  boolean start_flag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_book_scootr, container, false);
        context = this.getActivity();
        init();
        return view;

    }

    private  void init(){

    }

}
