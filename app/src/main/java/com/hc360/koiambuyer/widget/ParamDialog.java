package com.hc360.koiambuyer.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ParamDialog extends Dialog {

    Context mContext;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.tv_save_on)
    TextView mTvSaveOn;
    onSaveListener listener;
    PurchaseDetailInfo.StProductParasBean mInfo;
    //要增加或者修改的index
    int mPosition = -1;
    //现在的个数
    int mNum = -1;
    int mMaxNum ;

    public ParamDialog(Context context, int position, int num, int maxNum, @Nullable PurchaseDetailInfo.StProductParasBean info, onSaveListener listener) {
        super(context, R.style.BottomDialogStyle);
        this.mContext = context;
        this.listener = listener;
        this.mPosition = position;
        this.mInfo = info;
        this.mNum = num;
        this.mMaxNum = maxNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_publish_param);
        ButterKnife.bind(this);
        // 拿到Dialog的Window, 修改Window的属性
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);
        if (mInfo!=null){
            setNoMoreGoOn(mNum>=mMaxNum);
            mEtName.setText(mInfo.paramName);
            mEtName.setSelection(mInfo.paramName.length());
            mEtContent.setText(mInfo.paramValue);
            mEtContent.setSelection(mInfo.paramValue.length());
        }else {
            setNoMoreGoOn(mNum>=mMaxNum-1);
        }
        if (mPosition == mMaxNum){
            mTvSaveOn.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.fl_close, R.id.tv_save, R.id.tv_save_on})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                dismiss();
                break;
            case R.id.tv_save:
                if (getText(false)){
                    dismiss();
                }
                break;
            case R.id.tv_save_on:
                if (getText(true)){
//                    mEtName.setText("");
//                    mEtContent.setText("");
                    dismiss();
                }
                if (mInfo!=null){
                    mPosition = mNum+1;
                    setNoMoreGoOn(mPosition>=mMaxNum);
                }else {
                    mPosition++;
                    setNoMoreGoOn(mPosition>=mMaxNum);
                }
                break;
        }
    }

    private boolean getText(boolean goOn) {
        if (listener != null){
            String name = mEtName.getText().toString().trim();
            String content = mEtContent.getText().toString().trim();
            if (TextUtils.isEmpty(name)||TextUtils.isEmpty(content)){
                ToastUtil.showShort(mContext,mContext.getResources().getString(R.string.publish_param_msg));
                return false;
            }else {
                listener.onSave(name,content,mPosition,goOn);
                return true;
            }
        }
        return false;
    }

    private void setNoMoreGoOn(boolean noMore) {
        if (noMore){
            mTvSaveOn.setBackgroundColor(getContext().getResources().getColor(R.color.BgColor));
        }
    }

    public interface onSaveListener{
        void onSave(String name, String content, int position,boolean goOn);
    }


}
