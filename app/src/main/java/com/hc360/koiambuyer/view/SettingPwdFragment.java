package com.hc360.koiambuyer.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.DialogPositiveClickListener;
import com.hc360.koiambuyer.myinterface.ipresenter.ISettingPwdPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISettingPwdView;
import com.hc360.koiambuyer.presenter.SettingPwdPresenter;
import com.hc360.koiambuyer.rxbus.RxBus;
import com.hc360.koiambuyer.rxbus.event.FinishAccountEvent;
import com.hc360.koiambuyer.utils.DialogHelper;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.view.home.HomeActivity;
import com.hc360.koiambuyer.widget.SingleTextView;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SettingPwdFragment extends BaseFragment<ISettingPwdPresenter> implements ISettingPwdView {

    @BindView(R.id.et_set)
    EditText mEtSet;
    @BindView(R.id.et_confirm)
    EditText mEtConfirm;
    @BindView(R.id.stv)
    SingleTextView mStv;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.fl)
    FrameLayout mFl;
    private String mPhone;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_setting_pwd;
    }

    @Override
    protected void initInjector() {
        mPresenter = new SettingPwdPresenter(this);
    }

    @Override
    protected void initViews() {
        mStv.setText(getStr(R.string.save));
        Bundle args = getArguments();
        if (args != null) {
            mPhone = args.getString(Msg.PHONE);
        }
        mFl.setBackgroundColor(getResources().getColor(R.color.StvColor));
        mTvTitle.setText(getStr(R.string.set_pwd));
        mTvContent.setText(getStr(R.string.set_pwd_content));
        mStv.setText(getStr(R.string.save));
        if (TextUtils.isEmpty(SPUtils.getString(mContext, Constant._LOGIN_TYPE, ""))) {
            try {
                Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
                f.setAccessible(true);
                f.set(mEtSet, R.drawable.shape_et_cursor);
                f.set(mEtConfirm, R.drawable.shape_et_cursor);
            } catch (Exception ignored) {
                // TODO: handle exception
            }
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick(R.id.stv)
    public void onClick() {
        if (TextUtils.isEmpty(mPhone)) {
            //登录---更新密码
            String id = SPUtils.getString(mContext, Constant._ID, "");
            mPresenter.updatePwd(id, mEtSet.getText().toString().trim());
        } else if (mPhone.equals(Msg.UPDATE_PWD)) {
            //验证码登录---设置密码
            String id = SPUtils.getString(mContext, Constant.TEMP_ID, "");
            mPresenter.updatePwd(id, mEtSet.getText().toString().trim(), mEtConfirm.getText().toString().trim());
        } else {
            //注册---设置密码
            mPresenter.settingPwd(mPhone, mEtSet.getText().toString().trim(), mEtConfirm.getText().toString().trim());
        }
    }

    @Override
    public void settingSuccess() {
        //设置成功，弹出对话框
        DialogHelper.showCustomNoCancelDialog(getActivity(), getStr(R.string.dialog_default_title), getStr(R.string.register_success), null, new DialogPositiveClickListener() {
            @Override
            public void positiveClick() {
                //注册成功，暂时跳转到主页
                RxBus.getInstance().post(new FinishAccountEvent(true));
                startActivity(new Intent(mContext, HomeActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    public void updateSuccess(String initType) {
        RxBus.getInstance().post(new FinishAccountEvent(true));
        startActivity(new Intent(mContext, HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
