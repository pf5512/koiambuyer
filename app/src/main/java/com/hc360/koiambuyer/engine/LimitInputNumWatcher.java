package com.hc360.koiambuyer.engine;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class LimitInputNumWatcher implements TextWatcher {
    /**
     * et
     */
    private EditText et = null;

    private int DECIMAL_DIGITS = 2;
    private double DECIMAL_MAX_DIGITS = 10000000;

    /**
     * 构造方法
     *
     * @param et
     */
    public LimitInputNumWatcher(EditText et) {
        this.et = et;
    }

    /**
     * 构造方法
     *
     * @param et    et
     * @param regex 筛选条件
     */
    public LimitInputNumWatcher(EditText et, int regex) {
        this.et = et;
        DECIMAL_DIGITS = regex;
    }

    /**
     * 构造方法
     *
     * @param et    et
     * @param regex 筛选条件
     */
    public LimitInputNumWatcher(EditText et, int regex,double maxNum) {
        this.et = et;
        DECIMAL_DIGITS = regex;
    }

    private String preNum = "0";
    @Override
    public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
        preNum = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > DECIMAL_DIGITS) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + DECIMAL_DIGITS+1);
                et.setText(s);
                et.setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            et.setText(s);
            et.setSelection(2);
        }
        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                et.setText(s.subSequence(0, 1));
                et.setSelection(1);
                return;
            }
        }
        if (!TextUtils.isEmpty(s.toString())){
            if (Double.parseDouble(s.toString())>DECIMAL_MAX_DIGITS){
                et.setText(preNum);
                et.setSelection(preNum.length()-1);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    /**
     * 清除不符合条件的内容
     *
     * @param regex
     * @return
     */
    private String clearLimitStr(String regex, String str) {
        return str.replaceAll(regex, "");
    }
}
