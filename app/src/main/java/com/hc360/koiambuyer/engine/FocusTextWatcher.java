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

public class FocusTextWatcher implements TextWatcher {
    /**
     * et
     */
    private EditText et = null;

    public FocusTextWatcher(EditText et) {
        this.et = et;

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        et.findFocus();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


}
