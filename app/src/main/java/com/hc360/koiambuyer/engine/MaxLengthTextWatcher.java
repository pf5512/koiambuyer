package com.hc360.koiambuyer.engine;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.ToastUtil;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MaxLengthTextWatcher implements TextWatcher {
    private int maxNum = 0;
    private EditText et;
    private String msg;

    public MaxLengthTextWatcher(int maxNum, EditText et,OnAfterText onAfterText) {
        this.maxNum = maxNum;
        this.et = et;
        this.listener = onAfterText;
    }
    public MaxLengthTextWatcher(int maxNum, EditText et, String msg,OnAfterText onAfterText) {
        this.maxNum = maxNum;
        this.et = et;
        this.msg = msg;
        this.listener = onAfterText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Editable editable = et.getText();
        int len = editable.length();

        if (len > maxNum) {
            if (TextUtils.isEmpty(msg)){
                ToastUtil.showShort(et.getContext(),et.getContext().getResources().getString(R.string.max_text_length)+maxNum+et.getContext().getResources().getString(R.string.max_text_length_second));
            }else{
                ToastUtil.showShort(et.getContext(),msg);
            }

            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0, maxNum);
            et.setText(newStr);
            editable = et.getText();

            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if (selEndIndex > newLen) {
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable, selEndIndex);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        listener.onAfterText(s);
    }
    OnAfterText listener;
    public interface OnAfterText {
        void onAfterText(Editable s);
    }

    public void setOnAfterText(OnAfterText onAfterText){
        this.listener = onAfterText;
    }
}
