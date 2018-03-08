package com.hc360.koiambuyer.engine;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/12
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class DisFocusedTextView extends TextView {
    public DisFocusedTextView(Context context) {
        this(context, null);
        isFocused();
    }

    public DisFocusedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        isFocused();
    }

    public DisFocusedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        isFocused();
    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {}
}
