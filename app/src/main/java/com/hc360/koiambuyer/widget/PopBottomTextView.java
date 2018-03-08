package com.hc360.koiambuyer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.hc360.koiambuyer.R;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PopBottomTextView extends TextView {

    private static final int DEFAULT_BG_NORMAL = R.drawable.shape_pop_bottom_normal;
    private static final int DEFAULT_BG_PRESS = R.drawable.shape_pop_bottom_press;
    private static final int DEFAULT_TV_NORMAL_COLOR = R.color.StvColor;
    private static final int DEFAULT_TV_PRESS_COLOR = R.color.tvNormalColor;
    private int mBgNormal;
    private int mBgPress;
    private int mTvNormalColor;
    private int mTvPressColor;

    public PopBottomTextView(Context context) {
        this(context, null);
    }

    public PopBottomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopBottomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PopBottomTextView);
        try {
            mBgNormal = a.getResourceId(R.styleable.PopBottomTextView_bg_normal, DEFAULT_BG_NORMAL);
            mBgPress = a.getResourceId(R.styleable.PopBottomTextView_bg_press, DEFAULT_BG_PRESS);
            mTvNormalColor = a.getColor(R.styleable.PopBottomTextView_tv_normal_color, getResources().getColor(DEFAULT_TV_NORMAL_COLOR));
            mTvPressColor = a.getColor(R.styleable.PopBottomTextView_tv_press_color, getResources().getColor(DEFAULT_TV_PRESS_COLOR));
            setBackgroundResource(mBgNormal);
            setTextColor(mTvNormalColor);
        }catch (Exception e){

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(mBgPress);
                setTextColor(mTvPressColor);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                setBackgroundResource(mBgNormal);
                setTextColor(mTvNormalColor);
                break;
        }
        return super.onTouchEvent(event);
    }
}
