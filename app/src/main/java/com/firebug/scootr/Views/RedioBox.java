package com.firebug.scootr.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.firebug.scootr.R;


/**
 * Created by ebabu on 4/5/15.
 */
public class RedioBox extends RadioButton {
    private Typeface tf = null;
    private String customFont;
    public RedioBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFontRadioButton(context, attrs);
    }

    public RedioBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFontRadioButton(context, attrs);
    }

    public RedioBox(Context context) {
        super(context);

    }
    public boolean setCustomFontRadioButton(Context ctx, String asset) {
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            return false;
        }
        setTypeface(tf);
        return true;
    }

    private void setCustomFontRadioButton(Context ctx, AttributeSet attrs) {
        final TypedArray a = ctx.obtainStyledAttributes(attrs,
                R.styleable.CustomEditText);
        customFont = a.getString(R.styleable.CustomEditText_edittextfont);
        // custompwd = a.getString(R.styleable.CustomEditText_edittextpwd);

        setCustomFontRadioButton(ctx, customFont);
        a.recycle();
    }


}
