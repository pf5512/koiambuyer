package com.hc360.koiambuyer.engine;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/28
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class LimitInputTextWatcher implements TextWatcher {
    /**
     * et
     */
    private EditText et = null;
    /**
     * 筛选条件
     */
    private String regex;
    /**
     * 默认的筛选条件(正则:只能输入中文)
     */
    private String DEFAULT_REGEX = "[^\u4E00-\u9FA5]";

    /**
     * 构造方法
     *
     * @param et
     */
    public LimitInputTextWatcher(EditText et) {
        this.et = et;
        this.regex = DEFAULT_REGEX;
    }

    /**
     * 构造方法
     *
     * @param et    et
     * @param regex 筛选条件
     */
    public LimitInputTextWatcher(EditText et, String regex) {
        this.et = et;
        this.regex = regex;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String str = editable.toString();
        String inputStr = clearLimitStr(regex, str);
        et.removeTextChangedListener(this);
        // et.setText方法可能会引起键盘变化,所以用editable.replace来显示内容
        editable.replace(0, editable.length(), inputStr.trim());
        et.addTextChangedListener(this);
        if (!isText(regex, str)){
            ToastUtil.showShort(et.getContext(), MyApp.getAppContext().getResources().getString(R.string.han_input));
        }
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
    /**
     * 清除不符合条件的内容
     *
     * @param regex
     * @return
     */
    private boolean isText(String regex, String str) {
        String s = str.replaceAll(regex, "");
        return str.length()==s.length();
    }
}
