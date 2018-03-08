package com.hc360.koiambuyer.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SmartScrollView extends NestedScrollView {
    OnScrollViewAtEndListener listener;
    private int mTouchSlop;
    private int downX;
    private int downY;
    public SmartScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    public SmartScrollView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public SmartScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View)getChildAt(getChildCount()-1);
        int d = view.getBottom();
        d -= (getHeight()+getScrollY());
        if(d==0) {
            if (listener != null){
                listener.moreAction();
            }
        }
        super.onScrollChanged(l,t,oldl,oldt);
    }
    public void setOnScrollViewAtEndListener(OnScrollViewAtEndListener listener){
        this.listener = listener;
    }

    public interface OnScrollViewAtEndListener{
        void moreAction();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
