package com.hc360.koiambuyer.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Project: Ruhefu
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/3/25
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class HeightTool {
    /**
     * 获取底部虚拟键盘的高度
     */

    public static int getBottomKeyboardHeight(Activity activity){
        int screenHeight =  getAccurateScreenDpi(activity)[1];
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightDifference = screenHeight - dm.heightPixels;
        return heightDifference;
    }

    /**
     * 获取精确的屏幕大小
     */
    public static int[] getAccurateScreenDpi(Activity activity) {
        int[] screenWH = new int[2];
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            Class<?> c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, dm);
            screenWH[0] = dm.widthPixels;
            screenWH[1] = dm.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }
        return screenWH;
    }

    public static double getScreenSizeOfDevice(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double x = Math.pow(width,2);
        double y = Math.pow(height,2);
        double diagonal = Math.sqrt(x+y);

        int dens=dm.densityDpi;
        double screenInches = diagonal/(double)dens;
        return screenInches;
    }

}
