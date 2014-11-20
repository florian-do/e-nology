package com.enology.fonts;

/**
 * Created by Lolo on 05/11/2014.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontTextView extends TextView
{
    public FontTextView(Context context)
    {
        this(context, null);
        setFont();
    }

    public FontTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, android.R.attr.textViewStyle);
        setFont();
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setFont();
    }

    public void setFont() {
        Typeface mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeue-Thin.otf");
        setTypeface(mTypeface);
    }
}