package com.enology.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Lolo on 05/11/2014.
 */
public class FontEditText extends EditText
{

    public FontEditText(Context context)
    {
        super(context);
        setFont();
    }

    public FontEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setFont();
    }

    public FontEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setFont();
    }

    public void setFont()
    {
        Typeface mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeue-Thin.otf");
        setTypeface(mTypeface);
    }
}
