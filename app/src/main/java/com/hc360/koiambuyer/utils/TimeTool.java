package com.hc360.koiambuyer.utils;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.view.MyApp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project: Ruhefu
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/3/17
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class TimeTool {
    public static String getTime(long time) {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        Date date=new Date(time);
        return format.format(date);
    }
    public static String getHourSecond(long time) {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        Date date=new Date(time);
        return format.format(date);
    }

    public static String getTimeDetail(long time) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(time);
        return format.format(date);
    }

    public static String getTimeByHan(long time) {
        String day = "";
        SimpleDateFormat format1=new SimpleDateFormat("yyyy");
        Date date1=new Date(time);

        SimpleDateFormat format2=new SimpleDateFormat("MM");
        Date date2=new Date(time);

        SimpleDateFormat format3=new SimpleDateFormat("dd");
        Date date3=new Date(time);
        String yue = format2.format(date2);
        if (yue.startsWith("0")){
            yue  = yue.replace("0","");
        }
        String ri = format3.format(date3);
        if (ri.startsWith("0")){
            ri  = ri.replace("0","");
        }
        day = format1.format(date1)+getStr(R.string.year)+yue+getStr(R.string.month)+ri+getStr(R.string.day);
        return day;
    }
    public static String getTimeByHanYue(long time) {
        String day = "";
        SimpleDateFormat format2=new SimpleDateFormat("MM");
        Date date2=new Date(time);
        SimpleDateFormat format3=new SimpleDateFormat("dd");
        Date date3=new Date(time);
        String yue = format2.format(date2);
        if (yue.startsWith("0")){
            yue  = yue.replace("0","");
        }
        String ri = format3.format(date3);
        if (ri.startsWith("0")){
            ri  = ri.replace("0","");
        }
        day =yue+getStr(R.string.month)+ri+getStr(R.string.day);
        return day;
    }
    public static String getDay(long time) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(time);
        return format.format(date);
    }

    public static String getDayDip(long time) {
        return getTimeByHan(time);
    }

    public static String getMonth(long time) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
        Date date=new Date(time);
        return format.format(date);
    }

    public static String getHour(long time) {
        SimpleDateFormat format=new SimpleDateFormat("dd");
        Date date=new Date(time);
        return format.format(date);
    }
    public static String getMonthDay(long time) {
        SimpleDateFormat format=new SimpleDateFormat("MM-dd");
        Date date=new Date(time);
        return format.format(date);
    }

    public static String getLastTime(long time){
        long nowTime = System.currentTimeMillis();
        long dTime = nowTime - time;
        if (dTime >0){
            if (dTime < 1000*60*60){
                return dTime/(1000*60)+getStr(R.string.minute_before);
            }else if (dTime <1000*60*60*24){
                return dTime/(1000*60*60)+getStr(R.string.hour_before);
            }else if (dTime <1000*60*60*24*2){
                return getStr(R.string.yesterday);
            }else {
                return TimeTool.getTimeByHan(time);
            }
        }
        return TimeTool.getTimeByHan(time);
    }

    public static String getStr(int strRes){
        return MyApp.getAppContext().getResources().getString(strRes);
    }
}
