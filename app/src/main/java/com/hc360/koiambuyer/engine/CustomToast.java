package com.hc360.koiambuyer.engine;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.hc360.koiambuyer.view.MyApp;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/2/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class CustomToast {
    private static final String TAG = "CustomToast";
    private Context mContext;
    private Toast mToast;
    private final int ALWAYS_SHOW = 0;
    private boolean mIsNeedHide = false;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ALWAYS_SHOW:
                    if (mToast != null) {
                        if (!mIsNeedHide) {
                            Log.d(TAG,"toast show");
                            mToast.show();
                            mHandler.sendEmptyMessageDelayed(ALWAYS_SHOW,10);
                        } else {
                            Log.d(TAG,"toast hide");
                            mToast.cancel();
                            mHandler.removeMessages(ALWAYS_SHOW);
                            mIsNeedHide = false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public CustomToast(Context context){
        mContext = context;
    }

    public void alwaysShow(final String text){
        //防止在子线程中弹Toast导致应用Crash
        MyApp.getInstance().runUITask(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(MyApp.getInstance(), text, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(text);
                }
                mHandler.sendEmptyMessageDelayed(ALWAYS_SHOW,10);
            }
        });
    }

    /**
     * 隐藏Toast框
     */
    public void hide(){
        this.mIsNeedHide = true;
    }
}
