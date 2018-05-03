package com.hc360.koiambuyer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.ToastUtil;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SingleTextView extends TextView {
    private static final float RADIUS_DEFAULT = 4f;
    private static final long MIN_CLICK_DELAY_TIME = 500;
    private int mBgNormalColor;
    private int mBgPressColor;
    private int mRadius;
    private boolean mSelectable;
    private GradientDrawable mGg;
    private long preDownTime = -1;

    public SingleTextView(Context context) {
        this(context, null);
    }

    public SingleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SingleTextView);
        try {
            mBgNormalColor = a.getColor(R.styleable.SingleTextView_bg_normal_color, getResources().getColor(R.color.buyerColor));
            mBgPressColor = a.getColor(R.styleable.SingleTextView_bg_press_color, getResources().getColor(R.color.buyerDarkColor));
            mRadius = a.getDimensionPixelSize(R.styleable.SingleTextView_radius, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, RADIUS_DEFAULT, getResources().getDisplayMetrics()));
            mSelectable = a.getBoolean(R.styleable.SingleTextView_selectable, false);
            //创建drawable
            mGg = new GradientDrawable();
            mGg.setColor(mBgNormalColor);
            mGg.setCornerRadius(mRadius);
            setBackgroundDrawable(mGg);
        } finally {
            a.recycle();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long downTime = System.currentTimeMillis();
                if (downTime - preDownTime < MIN_CLICK_DELAY_TIME) {
                    //两次点击时长短于0.5s时，不处理点击事件
                    ToastUtil.showShort(this.getContext(), getResources().getString(R.string.single_tv_fast_msg));
                    preDownTime = downTime;
                    return false;
                }

                mGg.setColor(mBgPressColor);
                preDownTime = downTime;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                mGg.setColor(mBgNormalColor);
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setInitColor(int color){
        mGg.setColor(getResources().getColor(color));
        mGg.setCornerRadius(mRadius);
    }

    public void setBgNormalColorRes(int color){
        mBgNormalColor = getResources().getColor(color);
        mGg.setColor(mBgNormalColor);
        mGg.setCornerRadius(mRadius);
    }

    public void setBgPressColorRes(int color){
        mBgPressColor =getResources().getColor(color);
    }

    public void setBgColorRes(int color){
        mBgNormalColor = getResources().getColor(color);
        mGg.setColor(mBgNormalColor);
        mGg.setCornerRadius(mRadius);
        mBgPressColor =getResources().getColor(color);
    }

    public void setBgColorRes(int normalColor,int pressColor){
        mBgNormalColor = getResources().getColor(normalColor);
        mGg.setColor(mBgNormalColor);
        mGg.setCornerRadius(mRadius);
        mBgPressColor =getResources().getColor(pressColor);
    }
}
