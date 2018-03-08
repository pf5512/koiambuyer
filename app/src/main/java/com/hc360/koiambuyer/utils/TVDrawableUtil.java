package com.hc360.koiambuyer.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/22
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class TVDrawableUtil {

    public static void setRightByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(null, null, getTvDrawable(context,resId), null);
    }

    public static void setLeftByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(getTvDrawable(context,resId), null, null, null);
    }
    public static void setLeftByRes(Context context, int resId,int iconSize, TextView tv){
        tv.setCompoundDrawables(getTvDrawable(context,resId,iconSize), null, null, null);
    }

    public static void setLeftNormalByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(getTvNormalDrawable(context,resId), null, null, null);
    }

    public static void setTopByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(null, getTvDrawable(context,resId), null, null);
    }

    public static void setTopNormalByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(null, getTvNormalDrawable(context,resId), null, null);
    }
    public static void setTopByRes(Context context, int resId,int iconSize, TextView tv){
        tv.setCompoundDrawables(null, getTvDrawable(context,resId,iconSize), null, null);
    }

    public static void setBottomByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(null,null, null,  getTvDrawable(context,resId));
    }

    public static void setBottomNormalByRes(Context context, int resId, TextView tv){
        tv.setCompoundDrawables(null,null, null,  getTvNormalDrawable(context,resId));
    }
    public static void setBottomByRes(Context context, int resId,int iconSize, TextView tv){
        tv.setCompoundDrawables(null,null, null,  getTvDrawable(context,resId,iconSize));
    }

    public static Drawable getTvDrawable(Context context, int resId){
        Drawable nav=context.getResources().getDrawable(resId);
        nav.setBounds(0, 0, nav.getMinimumWidth()/2, nav.getMinimumHeight()/2);
        return nav;
    }
    public static Drawable getTvNormalDrawable(Context context, int resId){
        Drawable nav=context.getResources().getDrawable(resId);
        nav.setBounds(0, 0, nav.getMinimumWidth(), nav.getMinimumHeight());
        return nav;
    }
    public static Drawable getTvDrawable(Context context, int resId,int iconSize){
        Drawable nav=context.getResources().getDrawable(resId);
        nav.setBounds(0, 0, iconSize, iconSize);
        return nav;
    }
}
