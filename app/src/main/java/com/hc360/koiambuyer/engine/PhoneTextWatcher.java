package com.hc360.koiambuyer.engine;

import android.text.Editable;
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

public class PhoneTextWatcher implements TextWatcher {

    public PhoneTextWatcher(EditText etPhone) {
        this.etPhone = etPhone;
    }

    private EditText etPhone;
    private boolean isAdd;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (after == 1) {//增加
            isAdd = true;
        } else {
            isAdd = false;
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isAdd) {
            if (null != etPhone) {
                String str = s.toString();
                if (!str.endsWith(" ")) {
                    int length = s.length();
                    if (length == 3 || length == 8) {
                        String str1 = str + " ";//手动添加空格
                        etPhone.setText(str1);
                        etPhone.setSelection(str1.length());//光标移到最右边
                    }
                }
            }
        }
    }
}
