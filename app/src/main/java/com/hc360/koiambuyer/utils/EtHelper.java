package com.hc360.koiambuyer.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.engine.CustomTextWatcher;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class EtHelper {
    public static void etLimit(final EditText et, final TextView tv , final int number){
        tv.setText("0/"+number);
        et.addTextChangedListener(new CustomTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()>number){
                    ToastUtil.showShort(et.getContext(),et.getContext().getResources().getString(R.string.max_input)+number+et.getContext().getResources().getString(R.string.max_texts));
                    et.setText(s.subSequence(0,number));
                    et.setSelection(number);
                }else{
                    tv.setText(s.toString().trim().length()+"/"+number);
                }
            }
        });
    }

    public static void etLimitColor(final EditText et, final TextView tv , final int number){
        PicNumUtils.setPicNum(tv,0,number);
        et.addTextChangedListener(new CustomTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()>number){
                    ToastUtil.showShort(et.getContext(),et.getContext().getResources().getString(R.string.max_input)+number+et.getContext().getResources().getString(R.string.max_texts));
                    et.setText(s.subSequence(0,number));
                    et.setSelection(number);
                }else{
                    int length = s.toString().trim().length();
                    String sLength = String.valueOf(length);
                    SpannableStringBuilder style = new SpannableStringBuilder(sLength + "/" + number);
                    style.setSpan(new ForegroundColorSpan(tv.getResources().getColor( R.color.buyerColor)), 0, sLength.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tv.setText(style);
                }
            }
        });
    }
    public static String getContent(EditText et){
        return et.getText().toString().trim();
    }


}
