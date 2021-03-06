package com.hc360.koiambuyer.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.hc360.koiambuyer.R;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/1/11
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PicNumUtils {
    public static void setPicNum(TextView tv, int count, int maxCount){
        String s = String.valueOf(count);
        SpannableStringBuilder style = new SpannableStringBuilder(s + "/" + maxCount);
        style.setSpan(new ForegroundColorSpan(tv.getResources().getColor( R.color.buyerColor)), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(style);
    }
}
