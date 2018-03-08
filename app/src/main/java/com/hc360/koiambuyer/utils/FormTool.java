package com.hc360.koiambuyer.utils;

import android.text.TextUtils;
import android.widget.EditText;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FormTool {
    public static boolean isPhone(String text){

        String num = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(text)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return text.matches(num);
        }
    }
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public static boolean isForm(EditText et_first,EditText et_second,String form){
        String firstText = et_first.getText().toString().trim();
        String secondText = et_second.getText().toString().trim();
        if (!TextUtils.isEmpty(firstText)&&!TextUtils.isEmpty(secondText)){
            switch (form){
                case Constant.IS_PHONE:
                    return FormTool.isPhone(firstText);
                case Constant.IS_EMAIL:
                    return FormTool.isEmail(firstText);
                case Constant.IS_PHONE_AND_EMAIL:
                    return FormTool.isEmail(firstText)&&FormTool.isEmail(firstText);
            }
        }else {
            ToastUtil.showShort(et_first.getContext(),et_first.getContext().getResources().getString(R.string.input_can_not_empty));
            return false;
        }
        return true;
    }

    public static boolean isQQ(String str) {
        String  regex ="[1-9][0-9]{4,14}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();

    }

    /**
     * 判断是否是字母和数字的组合
     * @param s
     * @return
     */
    public static boolean isNumAndChar(String s){
        int m = 0;
        int n = 0;
        for(int i=s.length();--i>=0;){
            int chr=s.charAt(i);
            if((chr>=65&&chr<=90)||(chr>=97&&chr<=122)){
                m++;
            }
            if (chr>47&&chr<58){
                n++;
            }
        }
       return (m!=0)&&(n!=0);
    }
}
