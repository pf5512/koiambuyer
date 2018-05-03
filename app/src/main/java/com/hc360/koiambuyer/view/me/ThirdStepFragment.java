package com.hc360.koiambuyer.view.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.Msg;
import com.hc360.koiambuyer.myinterface.ipresenter.IThirdStepPresenter;
import com.hc360.koiambuyer.myinterface.iview.IThirdStepView;
import com.hc360.koiambuyer.presenter.ThirdStepPresenter;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.AccountActivity;
import com.hc360.koiambuyer.view.base.BaseFragment;
import com.hc360.koiambuyer.widget.SingleTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ThirdStepFragment extends BaseFragment<IThirdStepPresenter> implements IThirdStepView{

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.et_pwd_first)
    EditText mEtPwdFirst;
    @BindView(R.id.et_pwd_second)
    EditText mEtPwdSecond;
    @BindView(R.id.stv)
    SingleTextView mStv;
    @BindView(R.id.fl)
    FrameLayout mFl;
    private String mMode;
    private String mPhone;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_third_step;
    }

    @Override
    protected void initInjector() {
        mPresenter = new ThirdStepPresenter(this);
    }

    @Override
    protected void initViews() {
        Bundle args = getArguments();
        if (args != null) {
            mMode = args.getString(Constant.MODE);
            mPhone = args.getString(Msg.MSG);
        }
        mFl.setBackgroundColor(getResources().getColor(R.color.StvColor));
        if (mMode.equals(Constant.THIRD_STEP)){
            //忘记密码
            mTvTitle.setText("3."+getStr(R.string.third_step_title));
            mTvContent.setText(getStr(R.string.third_step_content));
            mStv.setText(getStr(R.string.third_step_stv));
        }else if (mMode.equals(Constant.THIRD_STEP_CHANGE)){
            mTvTitle.setText("2."+getStr(R.string.third_step_title));
            mTvContent.setText(getStr(R.string.third_step_content));
            mStv.setText(getStr(R.string.third_step_stv));
        }else if (mMode.equals(Constant.SETTING_PWD)){
            mTvTitle.setText(getStr(R.string.set_pwd));
            mTvContent.setText(getStr(R.string.set_pwd_content));
            mStv.setText(getStr(R.string.save));
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.stv)
    public void onClick() {
        String firstPwd = mEtPwdFirst.getText().toString().trim();
        String secondPwd = mEtPwdSecond.getText().toString().trim();
        if (firstPwd.equals(secondPwd)){
            if (firstPwd.length()>=6&&firstPwd.length()<=20){
                if (FormTool.isNumAndChar(firstPwd)){
                    mPresenter.setPwd(mPhone,firstPwd);
                }else {
                    ToastUtil.showShort(mContext,getStr(R.string.pwd_form));
                }
            }else {
                ToastUtil.showShort(mContext,getStr(R.string.pwd_six));
            }
        }else {
            ToastUtil.showShort(mContext,getStr(R.string.pwd_twin_error));
        }
    }

    @Override
    public void setPwdSuccess() {
        if (mMode.equals(Constant.THIRD_STEP)){
            //忘记密码
            startActivity(new Intent(mContext, AccountActivity.class));
        }else if (mMode.equals(Constant.THIRD_STEP_CHANGE)){
            ToastUtil.showShort(mContext,getStr(R.string.change_success));
            getActivity().finish();
        }
    }
}
