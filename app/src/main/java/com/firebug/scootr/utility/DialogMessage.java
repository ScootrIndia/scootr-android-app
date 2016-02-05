package com.firebug.scootr.utility;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.firebug.scootr.R;
import com.firebug.scootr.Views.MyTextView;


/**
 * Created by p on 7/24/2015.
 */
public class DialogMessage {

    public static Dialog showPopupMessage(String msg,Context context){


            final Dialog dd = new Dialog(context);
        try {
            dd.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dd.setContentView(R.layout.dialog_message);

            ((MyTextView) dd.findViewById(R.id.message)).setText(msg);
            ((Button) dd.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dd.dismiss();
                }
            });


            dd.show();
        }catch (Exception e ){


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                        dd.show();
                        }catch (Exception e1){}
                    }
                },1000);


        }
        return dd;
    }

}
