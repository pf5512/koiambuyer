package com.hc360.koiambuyer.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.myinterface.PhotoDialogClickListener;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PhotoDialog extends Dialog {

    Context mContext;
    PhotoDialogClickListener mListener;
    TextView mTvCamera;
    TextView mTvPhoto;
    TextView mTvCancel;

    public PhotoDialog(Context context, PhotoDialogClickListener listener) {
        // 在构造方法里, 传入主题
        super(context, R.style.BottomDialogStyle);
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_bottom_dialog_layout);
        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);
        mTvCamera = (TextView) findViewById(R.id.tv_camera);
        mTvPhoto = (TextView) findViewById(R.id.tv_photo);
        mTvCancel = (TextView) findViewById(R.id.tv_cancel);
        mTvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openCamera();
            }
        });
        mTvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openPhoto();
            }
        });
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });
    }

}
