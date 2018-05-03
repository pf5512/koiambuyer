package com.hc360.koiambuyer.api.bean;


import com.hyphenate.EMCallBack;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public abstract class MyEMCallBack implements EMCallBack {
    @Override
    public abstract void onSuccess();

    @Override
    public void onError(int i, String s) {

    }

    @Override
    public void onProgress(int i, String s) {

    }
}
