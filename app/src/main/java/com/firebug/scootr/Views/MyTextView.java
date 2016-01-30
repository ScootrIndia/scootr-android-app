package com.firebug.scootr.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;


import com.firebug.scootr.R;
import com.rey.material.widget.TextView;


/**
 * Created by ebabu on 30/4/15.
 */
public class MyTextView extends TextView {
    private Typeface tf = null;
    private String customFont;
    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFontTextView(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFontTextView(context, attrs);
    }

    public MyTextView(Context context) {
        super(context);

    }
    public boolean setCustomFontTextView(Context ctx, String asset) {
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            return false;
        }
        setTypeface(tf);
        return true;
    }

    private void setCustomFontTextView(Context ctx, AttributeSet attrs) {
        final TypedArray a = ctx.obtainStyledAttributes(attrs,
                R.styleable.CustomEditText);
        customFont = a.getString(R.styleable.CustomEditText_edittextfont);

        // custompwd = a.getString(R.styleable.CustomEditText_edittextpwd);

        setCustomFontTextView(ctx, customFont);
        a.recycle();
    }

}