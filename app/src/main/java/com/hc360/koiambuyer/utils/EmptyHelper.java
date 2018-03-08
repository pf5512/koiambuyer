package com.hc360.koiambuyer.utils;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.view.MyApp;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/12
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class EmptyHelper {
    public static String tvEmptyToast(TextView tv,String msg){
        String content = tv.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            ToastUtil.showShort(MyApp.getAppContext(),msg);
            return "";
        }else {
            return content;
        }
    }
    public static String etEmptyToast(EditText et, String msg){
        String content = et.getText().toString().trim();
        if (TextUtils.isEmpty(content)){
            ToastUtil.showShort(MyApp.getAppContext(),msg);
            return "";
        }else {
            return content;
        }
    }

    public static void isEmpty(String... msg){
        for (String s : msg) {
            if (TextUtils.isEmpty(s)){
                ToastUtil.showShort(MyApp.getAppContext(),MyApp.getAppContext().getString(R.string.input_can_not_empty));
                return;
            }
        }
    }
}
