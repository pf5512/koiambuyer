package com.hc360.koiambuyer.widget;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SmartSecondScrollNestedView extends NestedScrollView {
    OnScrollViewAtEndListener listener;
    public SmartSecondScrollNestedView(Context context) {
        super(context);
    }
    public SmartSecondScrollNestedView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
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
        if (listener != null){
            listener.onScroll(l,t,oldl,oldt);
        }
        super.onScrollChanged(l,t,oldl,oldt);
    }
    public void setOnScrollViewAtEndListener(OnScrollViewAtEndListener listener){
        this.listener = listener;
    }

    public interface OnScrollViewAtEndListener{
        void moreAction();
        void onScroll(int l, int t, int oldl, int oldt);
    }
}
