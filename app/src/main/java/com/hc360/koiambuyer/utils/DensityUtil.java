package com.hc360.koiambuyer.utils;

import android.content.Context;

public class DensityUtil {

	 /** 
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素) 
     */  
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  //获取手机的屏幕的密度
        return (int) (dpValue * scale + 0.5f); //+0.5是为了四舍五入   3.3  3   3.6 4   3.3 3  3.9  3
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
	
}
