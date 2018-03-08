package com.hc360.koiambuyer.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.view.MyApp;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class TabHelper {
    public static void changeTab(Context context, TextView selectTv,TextView normalTv){
        //改变tab标签下的线
        Drawable visibleBottomDrawable = null;
        if (TextUtils.isEmpty(MyApp.sLoginType)){
            visibleBottomDrawable = context.getResources().getDrawable(R.drawable.shape_account_tab_line);
            selectTv.setTextColor(context.getResources().getColor(R.color.mainColor));
        }else{
            visibleBottomDrawable = context.getResources().getDrawable(R.drawable.shape_account_tab_line);
            selectTv.setTextColor(context.getResources().getColor(MyApp.sLoginType.equals(Constant.BUYER)?R.color.buyerColor:R.color.sellerColor));
        }
        visibleBottomDrawable.setBounds(0, 0, visibleBottomDrawable.getMinimumWidth(), visibleBottomDrawable.getMinimumHeight());
        Drawable inVisibleBottomDrawable = context.getResources().getDrawable(R.drawable.shape_account_tab_line_none);
        inVisibleBottomDrawable.setBounds(0, 0, visibleBottomDrawable.getMinimumWidth(), visibleBottomDrawable.getMinimumHeight());
        selectTv.setCompoundDrawables(null, null, null, visibleBottomDrawable);
        normalTv.setCompoundDrawables(null, null, null, inVisibleBottomDrawable);
        normalTv.setTextColor(context.getResources().getColor(R.color.tvNormalColor));

    }

    public static void changeMainTab(Context context, TextView selectTv,TextView normalTv){
        //改变tab标签下的线
        Drawable visibleBottomDrawable = null;
        if (TextUtils.isEmpty(MyApp.sLoginType)){
            visibleBottomDrawable = context.getResources().getDrawable(R.drawable.shape_account_tab_line);
            selectTv.setTextColor(context.getResources().getColor(R.color.mainColor));
        }else{
            visibleBottomDrawable = context.getResources().getDrawable(R.drawable.shape_account_tab_line);
            selectTv.setTextColor(context.getResources().getColor(R.color.mainColor));
        }
        visibleBottomDrawable.setBounds(0, 0, visibleBottomDrawable.getMinimumWidth(), visibleBottomDrawable.getMinimumHeight());
        Drawable inVisibleBottomDrawable = context.getResources().getDrawable(R.drawable.shape_account_tab_line_none);
        inVisibleBottomDrawable.setBounds(0, 0, visibleBottomDrawable.getMinimumWidth(), visibleBottomDrawable.getMinimumHeight());
        selectTv.setCompoundDrawables(null, null, null, visibleBottomDrawable);
        normalTv.setCompoundDrawables(null, null, null, inVisibleBottomDrawable);
        normalTv.setTextColor(context.getResources().getColor(R.color.tvNormalColor));

    }
}
